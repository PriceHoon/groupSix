package com.sparta.spartagroupsixproject.controller;


import com.sparta.spartagroupsixproject.dto.BoardRequestDto;
import com.sparta.spartagroupsixproject.dto.BoardResponseDto;
import com.sparta.spartagroupsixproject.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

//     게시글 생성
    @PostMapping("/board/list")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        // 응답 보내기
        return boardService.createBoard(requestDto, request);

    }
    // 게시물 전체,이름 조회
//    @GetMapping("/board/list")
//    public ResponseEntity<List<BoardResponseDto>> getBoardByUsername@RequestParam(required = false) String username){
//        if (username == null){
//            return boardService.getBoardAll();
//        }else return  boardService.getBoardByUsername(username);
//    }



//     게시글 전체 조회
    @GetMapping("/board/list")
    public ResponseEntity<List<BoardResponseDto>> getBoardList(){
        return (ResponseEntity<List<BoardResponseDto>>) boardService.getBoardAll();
    }

   //@PostMapping("/board/list")
//    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardRequestDTO boardDto, HttpServletRequest request){
//
//        return boardService.createBoard(boardDto,request);
//    }


//    @GetMapping("/board/list/{id}")
//    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id){
//        return boardService.findBoardById(id);
//    }

//    @PutMapping("/board/list/{id}")
//    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDTO boardDto , HttpServletRequest request){
//        return boardService.update(id,boardDto,request);
//    }

//
//    @DeleteMapping("/board/list/{id}")
//    public ResponseEntity deleteBoard(@PathVariable Long id, HttpServletRequest request){
//        return boardService.delete(id,request);
//    }

}
