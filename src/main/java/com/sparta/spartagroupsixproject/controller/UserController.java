package com.sparta.spartagroupsixproject.controller;

import com.sparta.spartagroupsixproject.dto.LoginRequestDto;
import com.sparta.spartagroupsixproject.dto.SignupRequestDto;
import com.sparta.spartagroupsixproject.dto.StatusResponseDto;
import com.sparta.spartagroupsixproject.jwt.JwtUtil;
import com.sparta.spartagroupsixproject.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<StatusResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto) {
        String msg = userService.signup(requestDto);
        StatusResponseDto responseDto = new StatusResponseDto(msg, HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/login")

    public ResponseEntity<StatusResponseDto> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {

        String msg = userService.login(requestDto, response);
        StatusResponseDto responseDto = new StatusResponseDto(msg, HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}

//    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
//        String generatedToken = userService.login(loginRequestDto);
//
//        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, generatedToken);
