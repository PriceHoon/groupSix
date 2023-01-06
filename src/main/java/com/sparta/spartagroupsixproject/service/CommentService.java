package com.sparta.spartagroupsixproject.service;


import com.sparta.spartagroupsixproject.dto.CommentRequestDto;
import com.sparta.spartagroupsixproject.dto.CommentResponseDto;
import com.sparta.spartagroupsixproject.entity.Board;
import com.sparta.spartagroupsixproject.entity.Comment;
import com.sparta.spartagroupsixproject.entity.LikeComment;
import com.sparta.spartagroupsixproject.entity.User;
import com.sparta.spartagroupsixproject.jwt.JwtUtil;
import com.sparta.spartagroupsixproject.repository.BoardRepository;
import com.sparta.spartagroupsixproject.repository.CommentRepository;
import com.sparta.spartagroupsixproject.repository.LikeCommentRepository;
import com.sparta.spartagroupsixproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    private final LikeCommentRepository likeCommentRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public CommentResponseDto createComment(Long id, CommentRequestDto requestDto, User user) {

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        ); //해당 게시글 찾는 과정

        Comment comment = new Comment(requestDto, user, board,0L);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }


    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, User user, Long commentId) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("수정할 댓글이 없습니다.")
        );
        if (comment.isWriter(user.getId())) {
            comment.update(requestDto);
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            return commentResponseDto;
        }
        throw new IllegalArgumentException("댓글을 수정할 권한이 없습니다.");

    }


    @Transactional
    public ResponseEntity deleteComment(Long id, User user, Long commentId) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("삭제할 댓글이 없습니다.")
        );

        if (comment.isWriter(user.getId())) {
            commentRepository.delete(comment);
            return new ResponseEntity<>("삭제 성공!", HttpStatus.OK);
        }

        throw new IllegalArgumentException("댓글을 삭제할 권한이 없습니다.");
    }

    public CommentResponseDto getComment(Long commentId) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("조회 할 댓글이 없습니다.")
        );

        Long count = likeCommentRepository.findAllByComment(comment).stream().count();
        comment.updateLikeNum(count);

        return new CommentResponseDto(comment);

    }
}




