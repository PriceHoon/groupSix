package com.sparta.spartagroupsixproject.controller;


import com.sparta.spartagroupsixproject.dto.RestApiResponse;
import com.sparta.spartagroupsixproject.service.LikeCommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
        RestApiResponse restApiResponse = new RestApiResponse(HttpStatus.OK, msg);
        return new ResponseEntity(restApiResponse, HttpStatus.OK);
    }

    @PostMapping("/comment/{id}/dislike")
    public ResponseEntity cancelLike(@PathVariable Long id, HttpServletRequest request) throws Exception {
        String msg = likeCommentService.cancelFavorite(id, request);
        RestApiResponse restApiResponse = new RestApiResponse(HttpStatus.OK, msg);
        return new ResponseEntity(restApiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handlerApiRequestException(IllegalArgumentException e) {
        RestApiResponse restApiResponse = new RestApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(restApiResponse, HttpStatus.BAD_REQUEST);
    }
}
