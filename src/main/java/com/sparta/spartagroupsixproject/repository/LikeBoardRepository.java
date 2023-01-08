package com.sparta.spartagroupsixproject.repository;

import com.sparta.spartagroupsixproject.entity.Board;
import com.sparta.spartagroupsixproject.entity.LikeBoard;
import com.sparta.spartagroupsixproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeBoardRepository extends JpaRepository<LikeBoard,Long> {

    LikeBoard findAllByBoardIdAndUserId(Long boardId, Long userId);

}

