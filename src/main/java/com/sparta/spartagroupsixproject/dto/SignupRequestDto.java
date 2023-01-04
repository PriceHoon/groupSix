package com.sparta.spartagroupsixproject.dto;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    //정규표현식
    @Pattern(regexp="^[a-z0-9]*$")
    @Size(min=4, max=10)
    private String username;    //최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성

    @Pattern(regexp="^[a-zA-Z0-9]*$")
    @Size(min=8, max=15)
    private String password;    ////최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성
    private boolean admin = false;
    private String adminToken = "";
}