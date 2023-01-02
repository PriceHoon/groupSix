package com.sparta.spartagroupsixproject.controller;


import com.sparta.spartagroupsixproject.service.LikeCommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeCommentController {

    private final LikeCommentService likeCommentService;

    @PostMapping("/comment/{id}/like")
    public ResponseEntity update(@PathVariable Long id, HttpServletRequest request) throws Exception {
        String msg = likeCommentService.clickFavorite(id, request);
        return new ResponseEntity("msg : " + msg,HttpStatus.OK);
    }
    
}
