package com.sparta.spartagroupsixproject.controller;


import com.sparta.spartagroupsixproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardController {


    private final BoardService boardService;

//    @GetMapping("/board/list")
//    public ResponseEntity<List<BoardResponseDto>> getAll(){
//        return boardService.getAll();
//    }

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
