package com.sparta.spartagroupsixproject.controller;

import com.sparta.spartagroupsixproject.dto.CommentRequestDto;
import com.sparta.spartagroupsixproject.dto.CommentResponseDto;
import com.sparta.spartagroupsixproject.security.UserDetailsImpl;
import com.sparta.spartagroupsixproject.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/board/{id}/comment")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        CommentResponseDto commentResponseDto = commentService.createComment(id, requestDto, userDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDto);
    }

    @GetMapping("/board/comment/{commentId}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable Long commentId){
        CommentResponseDto commentResponseDto = commentService.getComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(commentResponseDto);
    }

    @PutMapping("/board/comment/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long commentId ,@RequestBody CommentRequestDto commentRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        CommentResponseDto commentResponseDto = commentService.updateComment(commentId,commentRequestDto,userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(commentResponseDto);
    }

    @DeleteMapping("/board/comment/{commentId}")
    public ResponseEntity deleteComment( @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId){
        String msg = commentService.deleteComment(userDetails.getUser(),commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(msg);
    }

    //대댓글 작업

    @PostMapping("/board/comment/{commentId}")
    public ResponseEntity<CommentResponseDto> createPlusComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        CommentResponseDto commentResponseDto = commentService.createPlusComment(commentId, requestDto, userDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDto);
    }
}
