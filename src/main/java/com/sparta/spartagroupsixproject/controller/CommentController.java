package com.sparta.spartagroupsixproject.controller;

import com.sparta.spartagroupsixproject.dto.CommentRequestDto;
import com.sparta.spartagroupsixproject.dto.CommentResponseDto;
import com.sparta.spartagroupsixproject.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/board/{id}/comment")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        CommentResponseDto commentResponseDto = commentService.createComment(id, requestDto, request);
        return ResponseEntity.status(HttpStatus.OK).body(commentResponseDto);
    }

    @PutMapping("/board/{id}/comment/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request, @PathVariable Long commentId ){
        CommentResponseDto commentResponseDto = commentService.updateComment(id, commentRequestDto,request,commentId);
        return ResponseEntity.status(HttpStatus.OK).body(commentResponseDto);
    }

    @DeleteMapping("/board/{id}/comment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long id, HttpServletRequest request, @PathVariable Long commentId){
        return commentService.deleteComment(id,request,commentId);
    }
}