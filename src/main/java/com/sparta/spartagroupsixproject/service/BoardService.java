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
        return  boardResponseDtoList;
    }

    //게시글 생성
    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            Board board = new Board(requestDto, user);
            boardRepository.saveAndFlush(board);

            return new BoardResponseDto(board);
        } else {
            return null;
        }
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
    public BoardResponseDto update(Long id, BoardRequestDto requestDto, HttpServletRequest request) {

        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        //선택한 게시글 찾기
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("수정하고자 하는 게시글이 없습니다!")
        );

        // 토큰이 있는 경우에만 게시글 수정 가능
        if (token != null) {

            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            if (board.getUser().getId() == user.getId() || user.getUserRoleEnum().equals(UserRoleEnum.ADMIN)) {

                board.update(requestDto);
                return new BoardResponseDto(board);
            } else {
                throw new IllegalArgumentException("해당 사용자 혹은 관리자가 아니면 게시글을 수정할 수 없습니다!");
            }

        }
        return null;


    }
    @Transactional
    public ResponseEntity<String> delete(Long id, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        //선택한 게시글 찾기
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("삭제하고자 하는 게시글이 없습니다!")
        );

        // 토큰이 있는 경우에만 게시글 삭제 가능
        if (token != null) {

            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            if (board.getUser().getId() == user.getId() || user.getUserRoleEnum().equals(UserRoleEnum.ADMIN)) {
                boardRepository.deleteById(id);
                return new ResponseEntity<>("게시글 삭제 성공.", HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("해당 사용자 혹은 관리자가 아니면 게시글을 삭제할 수 없습니다!");
            }
        }
        return null;
    }


}

