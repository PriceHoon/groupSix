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
    public String clickFavorite(Long commentId, User user) throws Exception {

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글은 존재하지않습니다"));

        if (!likeCommentRepository.existsByUserIdAndCommentId(user.getId(), commentId)) {
            LikeComment likeComment = LikeComment.builder().commentId(commentId).userId(user.getId()).build();
            likeCommentRepository.saveAndFlush(likeComment);
            comment.updateLikeNum(getCountLike(comment));
            return "좋아요를 누르셨습니다";
        } else {
            throw new Exception("해당 댓글에는 이미 좋아요를 눌렀습니다");
        }

    }
    @Transactional
    public String cancelFavorite(Long commentId, User user) throws Exception {

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글은 존재하지않습니다"));

        LikeComment likeComment = likeCommentRepository.findByUserIdAndCommentId(user.getId(),commentId);
        if (likeComment!=null) {
            likeCommentRepository.delete(likeComment);
//            comment.updateLikeNum(getCountLike(comment));
            return "좋아요를 취소하였습니다";
        } else {
            throw new Exception("해당 댓글에 좋아요를 누른 기록이 존재하지않습니다.");
        }
    }


    public Long getCountLike(Comment comment) {
        Long count = likeCommentRepository.findAllByCommentId(comment.getId()).stream().count();
        return count;
    }
}
