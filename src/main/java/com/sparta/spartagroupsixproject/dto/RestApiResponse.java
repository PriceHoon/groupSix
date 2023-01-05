package com.sparta.spartagroupsixproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestApiResponse {

    private HttpStatusCode httpStatusCode;
    private String msg;

}

