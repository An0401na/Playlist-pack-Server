package com.hositamtam.plypockets.user.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.google.gson.Gson;
import com.hositamtam.plypockets.controller.UserController;
import com.hositamtam.plypockets.dto.LoginDto;
import com.hositamtam.plypockets.dto.LoginResponseDTO;
import com.hositamtam.plypockets.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @DisplayName("회원가입 성공")
    @Test
    void LoginSuccess() throws Exception {
        //given
        LoginDto request = request();
        LoginResponseDTO response =response();

        doReturn(ResponseEntity.ok(response)).when(userService)
                .loginUser(any(LoginDto.class));

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );

        //then
        System.out.println(resultActions);

        MvcResult mvcResult = resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(response.getUserId()))
                .andExpect(jsonPath("$.nickname").value(response.getNickname()))
                .andReturn();
    }
    private LoginDto request() {
        return LoginDto.builder()
                .nickname("훈")
                .password("vdongv1620")
                .build();
    }

    private LoginResponseDTO response() {
        return LoginResponseDTO.builder()
                .userId(1L)
                .nickname("최동훈")
                .build();
    }
}
