package com.sparta.spartagroupsixproject.repository;

import com.sparta.spartagroupsixproject.entity.Comment;
import com.sparta.spartagroupsixproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long CommentId);

    void deleteAllByUserId(Long userId);
}

