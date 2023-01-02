package com.sparta.spartagroupsixproject.controller;

import com.sparta.spartagroupsixproject.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity singup(){

        return ResponseEntity.status(HttpStatus.OK).body("Dto 자리가 될겁니다..!");
    }

    @PostMapping("/login")
    public ResponseEntity login(HttpServletResponse response){
        //토큰을 보내기 위한 response객체
        //login할 때도 마찬가지로 dto가 필요합니다!

        //userService.login(loginRequestDto,response);
        return ResponseEntity.status(HttpStatus.OK).body("Dto 자리가 될겁니다..!");
    }


}
