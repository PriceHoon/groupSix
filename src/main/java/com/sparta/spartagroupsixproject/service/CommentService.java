package com.sparta.spartagroupsixproject.service;


import com.sparta.spartagroupsixproject.dto.CommentRequestDto;
import com.sparta.spartagroupsixproject.dto.CommentResponseDto;
import com.sparta.spartagroupsixproject.entity.*;
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

import java.util.List;

@Service
@RequiredArgsConstructor

public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    private final LikeCommentRepository likeCommentRepository;

    @Transactional
    public CommentResponseDto createComment(Long id, CommentRequestDto requestDto, Long userId) {

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        ); //해당 게시글 찾는 과정

        Comment comment = Comment.builder().boardId(board.getId()).content(requestDto.getContent()).userId(userId).likenum(0L).build();
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }


    @Transactional
    public CommentResponseDto updateComment( Long commentId,CommentRequestDto requestDto, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("수정할 댓글이 없습니다.")
        );
        if (comment.isWriter(user.getId())|| user.isAdmin()) {
            comment.update(requestDto);
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            return commentResponseDto;
        }
        throw new IllegalArgumentException("댓글을 수정할 권한이 없습니다.");

    }


    @Transactional
    public String deleteComment(User user, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("삭제할 댓글이 없습니다.")
        );

        if (comment.isWriter(user.getId())|| user.isAdmin()) {
            commentRepository.delete(comment);
            return "삭제 성공!";
        }

        throw new IllegalArgumentException("댓글을 삭제할 권한이 없습니다.");
    }

    public CommentResponseDto getComment(Long commentId) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("조회 할 댓글이 없습니다.")
        );

        Long count = likeCommentRepository.findAllByCommentId(commentId).stream().count();
        comment.updateLikeNum(count);

        return new CommentResponseDto(comment);

    }

    @Transactional
    public void deleteCommentByUser(Long userId){
        commentRepository.deleteAllByUserId(userId);
    }


    //대댓글 해보자..
    @Transactional
    public CommentResponseDto createPlusComment(Long commentId, CommentRequestDto requestDto, Long userId) {

        Comment parentComment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다.")
        ); //해당 댓글 찾는 과정

        Comment comment = Comment.builder().parent(parentComment).content(requestDto.getContent()).userId(userId).likenum(0L).boardId(parentComment.getBoardId()).build();
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }
}




