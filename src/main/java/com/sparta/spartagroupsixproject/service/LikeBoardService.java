package com.sparta.spartagroupsixproject.service;

import com.sparta.spartagroupsixproject.dto.LikeBoardResponseDto;
import com.sparta.spartagroupsixproject.entity.Board;
import com.sparta.spartagroupsixproject.entity.LikeBoard;
import com.sparta.spartagroupsixproject.entity.User;
import com.sparta.spartagroupsixproject.repository.BoardRepository;
import com.sparta.spartagroupsixproject.repository.LikeBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeBoardService {

    private final LikeBoardRepository likeBoardRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public LikeBoardResponseDto update(Long id, User user) {

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("좋아요를 누를 게시글이 없습니다!")
        );


        LikeBoard likeBoard = likeBoardRepository.findAllByBoardIdAndUserId(id, user.getId());

        if (likeBoard == null) {

            LikeBoard likeBoardFirst = LikeBoard.builder().boardId(board.getId()).isCheck(true).userId(user.getId()).build();
            likeBoardRepository.saveAndFlush(likeBoardFirst);

            Long updateLikeNum = getLikeNum(board);
            board.updateLikeNum(updateLikeNum);
            log.info("좋아요 최초생성! 갯수 => " + updateLikeNum);

            return new LikeBoardResponseDto("좋아요 최초생성!", HttpStatus.OK.value());

        } else {

            if(likeBoard.isCheck()){
                return new LikeBoardResponseDto("이미 좋아요가 눌려있어요!!", HttpStatus.BAD_REQUEST.value());
            }

            likeBoard.update(true);
            log.info("좋아요 다시 생성!(눌림)");
            Long updateLikeNum = getLikeNum(board);
            board.updateLikeNum(updateLikeNum);
            log.info("좋아요 다시 생성!(눌림) 갯수 => " + updateLikeNum);
            return new LikeBoardResponseDto("좋아요 다시 생성!(눌림)", HttpStatus.OK.value());
        }
    }


    @Transactional
    public LikeBoardResponseDto cancelLike(Long id, User user) {

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("좋아요 취소하기 위한 게시글이 없습니다!")
        );

        LikeBoard likeBoard = likeBoardRepository.findAllByBoardIdAndUserId(id, user.getId());

        if (likeBoard == null) {

            return new LikeBoardResponseDto("좋아요 누른적 없음!", HttpStatus.BAD_REQUEST.value());

        } else {

            if (likeBoard.isCheck()) {

                likeBoard.update(false);
                log.info("좋아요 다시 눌러서 풀림!");

                Long updateLikeNum = getLikeNum(board);
                board.updateLikeNum(updateLikeNum);
                log.info("좋아요 다시 눌러서 풀림! 갯수 => " + updateLikeNum);
                return new LikeBoardResponseDto("좋아요 다시 눌러서 풀림!", HttpStatus.OK.value());

            } else {
                return new LikeBoardResponseDto("이미 좋아요를 취소하셨습니다!", HttpStatus.BAD_REQUEST.value());
            }
        }

    }


    public Long getLikeNum(Board board) {
        return board.getLikenum();
    }


}
