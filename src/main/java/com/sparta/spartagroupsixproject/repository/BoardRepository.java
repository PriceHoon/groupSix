package com.sparta.spartagroupsixproject.repository;

import com.sparta.spartagroupsixproject.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findAllByOrderByModifiedAtDesc();
}