package com.sparta.spartagroupsixproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartagroupsixproject.dto.LoginRequestDto;
import com.sparta.spartagroupsixproject.dto.SignupRequestDto;
import com.sparta.spartagroupsixproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.sparta.spartagroupsixproject.controller.ApiDocumentUtils.getDocumentRequest;
import static com.sparta.spartagroupsixproject.controller.ApiDocumentUtils.getDocumentResponsee;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class UserControllerTest {

    /***
     * ???????????? ?????????
     * REST API status ?????? ?????????
     * validation - request ???????????? ????????? ??????????????? ???????????? ?????? ????????? ???????????????
     * security????????? ????????? controller ??????????????? ????????? ??? ?????????
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

        //given
        SignupRequestDto requestDto = SignupRequestDto.builder()
                .username("asdf123456")
                .password("asdasfsfsa123")
                .admin(false)
                .adminToken("")
                .build();

//        given(userService.signup(eq(1L),any(SignupRequestDto.class)))

//        String msg = "??????????????? ?????????????????????";



        mockMvc.perform(post("/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestDto))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andDo(document("userController",
                        getDocumentRequest(),
                        getDocumentResponsee(),
//                        pathParameters(
////                                parameterWithName("username").description("String-username")
//                        ),
                        requestFields(
                                fieldWithPath("username").type(JsonFieldType.STRING).description("??????_??????"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("??????_????????????"),
                                fieldWithPath("admin").type(JsonFieldType.BOOLEAN).description("??????_??????"),
                                fieldWithPath("adminToken").type(JsonFieldType.STRING).description("?????? ????????????")
                        ),
                        responseFields(
                                fieldWithPath("httpStatusCode").type(JsonFieldType.STRING).description("????????????"),
                                fieldWithPath("msg").type(JsonFieldType.NULL).description("???????????????")
                        )
                        ));


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
