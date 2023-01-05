package com.sparta.spartagroupsixproject.controller;


import com.sparta.spartagroupsixproject.dto.RestApiResponse;
import com.sparta.spartagroupsixproject.jwt.Filter;
import com.sparta.spartagroupsixproject.security.UserDetailsImpl;
import com.sparta.spartagroupsixproject.service.LikeCommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeCommentController {

    private final LikeCommentService likeCommentService;
    private final Filter filter;

    // 좋아요 클릭
    @PostMapping("/comment/{id}/like")
    public ResponseEntity update(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        String msg = likeCommentService.clickFavorite(id, userDetails.getUser());
        RestApiResponse restApiResponse = new RestApiResponse(HttpStatus.OK, msg);
        return ResponseEntity.status(HttpStatus.OK).body(restApiResponse);
    }

    // 좋아요 취소
    @DeleteMapping("/comment/{id}/dislike")
    public ResponseEntity cancelLike(@PathVariable Long id,  @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        String msg = likeCommentService.cancelFavorite(id, userDetails.getUser());
        RestApiResponse restApiResponse = new RestApiResponse(HttpStatus.OK, msg);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(restApiResponse);
    }

    // 좋아요 조회 -> 생각해보니 조회만 하는 기능이라 user 인증이 필요없을 것 같음
    @GetMapping("/comment/{id}/like")
    public ResponseEntity getCountLike(@PathVariable Long id){
        String msg = likeCommentService.getCountLike(id);
        RestApiResponse restApiResponse = new RestApiResponse(HttpStatus.OK, msg);
        return ResponseEntity.status(HttpStatus.OK).body(restApiResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handlerApiRequestException(IllegalArgumentException e) {

        RestApiResponse restApiResponse = new RestApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(restApiResponse, HttpStatus.BAD_REQUEST);
    }
}
