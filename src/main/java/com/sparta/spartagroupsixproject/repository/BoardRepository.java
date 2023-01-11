package com.sparta.spartagroupsixproject.repository;

import com.sparta.spartagroupsixproject.entity.Board;

import com.sparta.spartagroupsixproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findAllByOrderByModifiedAtDesc();


    void deleteAllByUserId(Long id);
}
