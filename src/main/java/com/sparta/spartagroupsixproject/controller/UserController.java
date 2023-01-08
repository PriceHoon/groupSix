package com.sparta.spartagroupsixproject.controller;

import com.sparta.spartagroupsixproject.dto.LoginRequestDto;
import com.sparta.spartagroupsixproject.dto.RestApiResponse;
import com.sparta.spartagroupsixproject.dto.SignupRequestDto;
import com.sparta.spartagroupsixproject.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity signup(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingResult) {

        //아이디 패스워드의 형식이 맞는지 검증 (완료)

        if (bindingResult.hasErrors()) {
            RestApiResponse restApiResponse = new RestApiResponse(HttpStatus.BAD_REQUEST,"형식에 맞게 작성 해주세요!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restApiResponse);
        }

        String msg = userService.signup(requestDto);
        RestApiResponse restApiResponse = new RestApiResponse(HttpStatus.CREATED,msg);

        return ResponseEntity.status(HttpStatus.CREATED).body(restApiResponse);
    }

    @PostMapping("/login")

    public ResponseEntity login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {

        String msg = userService.login(requestDto, response);
        RestApiResponse restApiResponse = new RestApiResponse(HttpStatus.OK,msg);

        return ResponseEntity.status(HttpStatus.OK).body(restApiResponse);
    }
}

//    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
//        String generatedToken = userService.login(loginRequestDto);
//
//        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, generatedToken);
