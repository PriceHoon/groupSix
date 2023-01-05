package com.sparta.spartagroupsixproject.service;


import com.sparta.spartagroupsixproject.dto.BoardRequestDto;
import com.sparta.spartagroupsixproject.dto.BoardResponseDto;
import com.sparta.spartagroupsixproject.entity.Board;
import com.sparta.spartagroupsixproject.entity.User;
import com.sparta.spartagroupsixproject.entity.UserRoleEnum;
import com.sparta.spartagroupsixproject.jwt.JwtUtil;
import com.sparta.spartagroupsixproject.repository.BoardRepository;
import com.sparta.spartagroupsixproject.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor


public class BoardService {


    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    //게시글 전체 조회
    @Transactional
    public List<BoardResponseDto> getBoardAll() {

        List<Board> boards = boardRepository.findAllByOrderByModifiedAtDesc();
        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();


        for (Board board : boards) {
            boardResponseDtoList.add(new BoardResponseDto(board));
        }
        return boardResponseDtoList;
    }

    //게시글 생성
    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto, User user) {

        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Board board = new Board(requestDto, user);
        boardRepository.saveAndFlush(board);

        return new BoardResponseDto(board);

    }

    // 게시글 선택 조회
    @Transactional
    public Optional<BoardResponseDto> findBoardById(Long id) {
        return Optional.ofNullable(new BoardResponseDto(boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("특정 게시글이 없습니다!")
        )));
    }

    // 게시물 수정
    @Transactional
    public BoardResponseDto update(Long id, BoardRequestDto requestDto, User user) {


        //선택한 게시글 찾기
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("수정하고자 하는 게시글이 없습니다!")
        );
        if (board.isAuthenticatedUser(user.getId()) || user.isAdmin()) {

            board.update(requestDto);
            return new BoardResponseDto(board);
        } else {
            throw new IllegalArgumentException("해당 사용자 혹은 관리자가 아니면 게시글을 수정할 수 없습니다!");
            //이렇게 터트리면 코드의 오류가 되어버림
            // 인가가 안된 사용자의 요청 때문에 예외가 터진건데 -> 코드가 문제가 있다!
        }

    }


    @Transactional
    public String delete(Long id, User user) {

        //선택한 게시글 찾기
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("삭제하고자 하는 게시글이 없습니다!")
        );

        if (board.isAuthenticatedUser(user.getId()) || user.isAdmin()) {
            boardRepository.deleteById(id);
            return "게시글 삭제 성공!";
        } else {
            throw new IllegalArgumentException("해당 사용자 혹은 관리자가 아니면 게시글을 삭제할 수 없습니다!");
        }
    }
}

