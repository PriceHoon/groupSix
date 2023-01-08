package com.sparta.spartagroupsixproject.repository;

import com.sparta.spartagroupsixproject.entity.Comment;
import com.sparta.spartagroupsixproject.entity.LikeComment;
import com.sparta.spartagroupsixproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeCommentRepository extends JpaRepository<LikeComment,Long> {

    //    LikeComment findByUserIdAndCommentId(, Comment comment);
    boolean existsByUserIdAndCommentId(Long userId, Long commentId);

    List<LikeComment> findAllByCommentId(Long commentId);

    LikeComment findByUserIdAndCommentId(Long userId, Long commentId);
}

