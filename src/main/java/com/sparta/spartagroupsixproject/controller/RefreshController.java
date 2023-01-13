package com.sparta.spartagroupsixproject.controller;


import com.sparta.spartagroupsixproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RefreshController {

    private final UserService userService;

    @PostMapping("/refresh")
    public ResponseEntity shotRefreshToken(HttpServletRequest request,HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAccessToken(request,response));
    }
    /***
     * 1. AT가 만기가 되면 해당 post 메서드를 프론트
     * 2.
     */

}
