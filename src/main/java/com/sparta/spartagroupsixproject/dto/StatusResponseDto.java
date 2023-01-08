package com.sparta.spartagroupsixproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@NoArgsConstructor //빈생성자 생성
@AllArgsConstructor //선언한게 들어가는 생성자 만듬
public class StatusResponseDto {

    private String msg;
    private HttpStatusCode httpStatusCode;
}

