package com.sparta.spartagroupsixproject.controller;


import com.sparta.spartagroupsixproject.dto.RestApiResponse;
import com.sparta.spartagroupsixproject.jwt.Filter;
import com.sparta.spartagroupsixproject.service.LikeCommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeCommentController {

    private final LikeCommentService likeCommentService;
    private final Filter filter;

    // 좋아요 클릭
    @PostMapping("/comment/{id}/like")
    public ResponseEntity update(@PathVariable Long id, HttpServletRequest request) throws Exception {
        String username = filter.checkUser(request);
        String msg = likeCommentService.clickFavorite(id, username);
        RestApiResponse restApiResponse = new RestApiResponse(HttpStatus.OK, msg);
        return new ResponseEntity(restApiResponse, HttpStatus.OK);
    }

    // 좋아요 취소
    @PostMapping("/comment/{id}/dislike")
    public ResponseEntity cancelLike(@PathVariable Long id, HttpServletRequest request) throws Exception {
        String username = filter.checkUser(request);
        String msg = likeCommentService.cancelFavorite(id, username);
        RestApiResponse restApiResponse = new RestApiResponse(HttpStatus.OK, msg);
        return new ResponseEntity(restApiResponse, HttpStatus.OK);
    }

    // 좋아요 조회
    @GetMapping("/comment/{id}/like")
    public ResponseEntity getCountLike(@PathVariable Long id, HttpServletRequest request){
        String msg = likeCommentService.getCountLike(id);
        RestApiResponse restApiResponse = new RestApiResponse(HttpStatus.OK, msg);
        return new ResponseEntity(restApiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handlerApiRequestException(IllegalArgumentException e) {

        RestApiResponse restApiResponse = new RestApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(restApiResponse, HttpStatus.BAD_REQUEST);
    }
}
