package com.sparta.spartagroupsixproject.service;

import com.sparta.spartagroupsixproject.entity.Comment;
import com.sparta.spartagroupsixproject.entity.LikeComment;
import com.sparta.spartagroupsixproject.entity.User;
import com.sparta.spartagroupsixproject.repository.CommentRepository;
import com.sparta.spartagroupsixproject.repository.LikeCommentRepository;
import com.sparta.spartagroupsixproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeCommentService {

    private final LikeCommentRepository likeCommentRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    @Transactional
    public String clickFavorite(Long CommentId, String username) throws Exception {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new IllegalArgumentException("권한이 없는 회원입니다."));
        Comment comment = commentRepository.findById(CommentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글은 존재하지않습니다"));

        if (!likeCommentRepository.existsByUserAndComment(user, comment)) {
            LikeComment likeComment = new LikeComment(user, comment);
            likeCommentRepository.save(likeComment);
            return "좋아요를 누르셨습니다";
        } else {
            throw new Exception("해당 댓글에는 이미 좋아요를 눌렀습니다");
        }

    }

    @Transactional
    public String cancelFavorite(Long CommentId, String username) throws Exception {

        User user = userRepository.findByUsername(username).orElseThrow(()-> new IllegalArgumentException("권한이 없는 회원입니다."));
        Comment comment = commentRepository.findById(CommentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글은 존재하지않습니다"));

        if (likeCommentRepository.existsByUserAndComment(user, comment)) {
            LikeComment likeComment = new LikeComment(user, comment);
            likeCommentRepository.delete(likeComment);
            return "좋아요를 취소하였습니다";
        } else {
            throw new Exception("해당 댓글에 좋아요를 누른 기록이 존재하지않습니다.");
        }
    }


}
