package com.sparta.spartagroupsixproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeBoardResponseDto {

    private String msg;
    private int statusCode;

    public LikeBoardResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}

