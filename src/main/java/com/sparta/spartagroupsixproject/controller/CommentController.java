package com.sparta.spartagroupsixproject.controller;


import com.sparta.spartagroupsixproject.dto.CommentRequestDto;
import com.sparta.spartagroupsixproject.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    //댓글 기능 구현
//    @PostMapping("/board/{id}/comment")
//    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){
//
//        return commentService.createComments(id,requestDto,request);
//    }
//
//    @PutMapping("/board/{id}/comment/{commentId}")
//    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto,HttpServletRequest request,@PathVariable Long commentId ){
//        return commentService.updateComment(id,commentRequestDto,request,commentId);
//    }
//
//    @DeleteMapping("/board/{id}/comment/{commentId}")
//    public ResponseEntity deleteComment(@PathVariable Long id, HttpServletRequest request, @PathVariable Long commentId){
//        return commentService.deleteComment(id,request,commentId);
//    }
}
