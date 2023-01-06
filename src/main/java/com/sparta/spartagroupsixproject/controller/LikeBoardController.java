package com.sparta.spartagroupsixproject.controller;


import com.sparta.spartagroupsixproject.entity.User;
import com.sparta.spartagroupsixproject.jwt.Filter;
import com.sparta.spartagroupsixproject.repository.UserRepository;
import com.sparta.spartagroupsixproject.security.UserDetailsImpl;
import com.sparta.spartagroupsixproject.service.LikeBoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeBoardController {

    private final LikeBoardService likeBoardService;


    @PostMapping("/board/{id}/like")
    public ResponseEntity update(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(likeBoardService.update(id,userDetails.getUser()));
    }


    @GetMapping("/board/{id}/like")
    public ResponseEntity cancelLike(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(likeBoardService.cancelLike(id,userDetails.getUser()));
    }

}
