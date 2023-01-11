package com.sparta.spartagroupsixproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartagroupsixproject.dto.LoginRequestDto;
import com.sparta.spartagroupsixproject.dto.SignupRequestDto;
import com.sparta.spartagroupsixproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
class UserControllerTest {

    /***
     * 컨트롤러 테스트
     * REST API status 코드 세분화
     * validation - request 데이터에 맞춰서 상태코드를 반환하는 것을 테스트 ★★★★★
     * security에서도 맞춰서 controller 테스트에서 반응할 수 있도록
     */
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @Test
    @WithUserDetails
    void signup() throws Exception {
        SignupRequestDto requestDto = SignupRequestDto.builder().username("asdf123456").password("asdasfsfsa123").admin(false).adminToken("").build();

        mockMvc.perform(post("/user/signup").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(requestDto)).with(csrf()))
                .andExpect(status().isCreated());

    }

    @Test
    @WithUserDetails
    void login() throws Exception {
        LoginRequestDto requestDto = LoginRequestDto.builder().username("asdf123456").password("asdfas123").build();

        mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(requestDto)).with(csrf()))
                .andExpect(status().isOk());
//                .andExpect(content().)
    }


}
