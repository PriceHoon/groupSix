package com.sparta.spartagroupsixproject.controller;


import com.sparta.spartagroupsixproject.dto.BoardRequestDto;
import com.sparta.spartagroupsixproject.dto.BoardResponseDto;
import com.sparta.spartagroupsixproject.dto.PageDto;
import com.sparta.spartagroupsixproject.entity.Board;
import com.sparta.spartagroupsixproject.security.UserDetailsImpl;
import com.sparta.spartagroupsixproject.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;



    // 게시글 전체 조회
    @GetMapping("/board/list")
    public ResponseEntity getBoardList(
            @RequestBody PageDto pageDto
            ) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getBoardAll(pageDto));

    }

    //      게시글 생성
    @PostMapping("/board/list")
    public ResponseEntity createBoard(@RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 응답 보내기
        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.createBoard(requestDto, userDetails.getUser()));
    }

    //선택 게시글 조회
    @GetMapping("/board/list/{id}")
    public ResponseEntity findBoard(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(boardService.findBoardById(id));
    }


    //게시글 수정
    @PutMapping("/board/list/{id}")
    public ResponseEntity updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.update(id, requestDto, userDetails.getUser()));
    }

    //게시글 삭제
    @DeleteMapping("/board/list/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String msg = boardService.delete(id, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(msg);
    }


}

//        @GetMapping("/board/list/{username}")
//        public Optional<BoardResponseDto> findUsername(@PathVariable String username){
//        return boardService.findBoardByUsername(username);
//        }

//    @PutMapping("/board/list/{id}")
//    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDTO boardDto , HttpServletRequest request){
//        return boardService.update(id,boardDto,request);
//    }

//
//    @DeleteMapping("/board/list/{id}")
//    public ResponseEntity deleteBoard(@PathVariable Long id, HttpServletRequest request){
//        return boardService.delete(id,request);
//    }


