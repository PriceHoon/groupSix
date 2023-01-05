package com.sparta.spartagroupsixproject.controller;

import com.sparta.spartagroupsixproject.dto.CommentRequestDto;
import com.sparta.spartagroupsixproject.dto.CommentResponseDto;
import com.sparta.spartagroupsixproject.security.UserDetailsImpl;
import com.sparta.spartagroupsixproject.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/board/{id}/comment")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        CommentResponseDto commentResponseDto = commentService.createComment(id, requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(commentResponseDto);
    }

    @PutMapping("/board/{id}/comment/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id,@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails  ){
        CommentResponseDto commentResponseDto = commentService.updateComment(id,commentId, commentRequestDto,userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(commentResponseDto);
    }

    @DeleteMapping("/board/{id}/comment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long id, @PathVariable Long commentId,@AuthenticationPrincipal UserDetailsImpl userDetails ){
       String msg =  commentService.deleteComment(id,commentId,userDetails.getUser());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(msg);
    }
}