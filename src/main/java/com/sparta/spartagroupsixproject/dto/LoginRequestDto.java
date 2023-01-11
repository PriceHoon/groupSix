package com.sparta.spartagroupsixproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class LoginRequestDto {
    private String username;
    private String password;
}
