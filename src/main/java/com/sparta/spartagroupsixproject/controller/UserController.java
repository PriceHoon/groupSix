package com.sparta.spartagroupsixproject.controller;

import com.sparta.spartagroupsixproject.dto.LoginRequestDto;
import com.sparta.spartagroupsixproject.dto.SignupRequestDto;
import com.sparta.spartagroupsixproject.dto.StatusResponseDto;
import com.sparta.spartagroupsixproject.jwt.JwtUtil;
import com.sparta.spartagroupsixproject.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<StatusResponseDto> singup(@RequestBody SignupRequestDto requestDto){

        String msg = userService.signup(requestDto);
        StatusResponseDto responseDto = new StatusResponseDto(msg, HttpStatus.OK);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String generatedToken = userService.login(loginRequestDto);

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, generatedToken);

        return "로그인 성공!";
    }


//    public ResponseEntity login(HttpServletResponse response){
//        //토큰을 보내기 위한 response객체
//        //login할 때도 마찬가지로 dto가 필요합니다!
//
//        //userService.login(loginRequestDto,response);
//        return ResponseEntity.status(HttpStatus.OK).body("Dto 자리가 될겁니다..!");
//    }


}
