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
        RestApiResponse restApiResponse = new RestApiResponse(HttpStatus.NO_CONTENT, msg);
        return ResponseEntity.status(HttpStatus.OK).body(restApiResponse);
    }

}
