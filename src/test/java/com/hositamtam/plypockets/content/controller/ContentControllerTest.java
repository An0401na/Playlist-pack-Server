package com.hositamtam.plypockets.content.controller;

import com.hositamtam.plypockets.content.ContentFixture;
import com.hositamtam.plypockets.controller.ContentController;
import com.hositamtam.plypockets.domain.Content;
import com.hositamtam.plypockets.service.ContentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContentController.class)
class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentService contentService;

    @Test
    @DisplayName("디지털 컨텐츠 내용을 조회한다.")
    void getContentDetailsTest() throws Exception {

        // given: 테스트 데이터 설정
        Content content = ContentFixture.DEFAULT.getContent(); // 데이터 설정
        BDDMockito.given(contentService.searchContent(1L)).willReturn(content); // contentService.searchContent(1L) 호출시 1L에 해당하는 가상의 Content 객체인 content를 반환하도록 설정

        // when: 컨트롤러 엔드포인트 호출
        MvcResult result = mockMvc.perform( //HTTP 요청 수행
                MockMvcRequestBuilders.patch("/contents/{contentId}", 1L) // 요청을 생성하고 해당 URL로 patch 메소드 호출하는 시뮬레이션
                        .contentType(MediaType.APPLICATION_JSON)) // HTTP 요청 헤더에 Content-Type을 설정하여 서버에게 요청 본문이 JSON 형식임을 알림
                .andReturn(); // HTTP 요청을 싱해하고 결과를 반환


        // then: 컨트롤러 응답 검증
        mockMvc.perform(MockMvcRequestBuilders.patch("/contents/{contentId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // HTTP 응답의 상태 코드가 200 (OK)인지 검증
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // 응답의 Content-Type이 JSON 형식인지 검증
                .andExpect(jsonPath("$.results").exists()) // 응답 JSON에 'results' 필드가 존재하는지 검증
                .andExpect(jsonPath("$.results.likeCnt").value(content.getLikeCnt())) // 'results' 필드 아래의 'likeCnt' 값이 기대한 값과 일치하는지 검증
                .andExpect(jsonPath("$.results.totalVoteCnt").value(content.getTotalVoteCnt())) // 'results' 필드 아래의 'totalVoteCnt' 값이 기대한 값과 일치하는지 검증
                .andExpect(jsonPath("$.results.viewCnt").value(content.getViewCnt())); // 'results' 필드 아래의 'viewCnt' 값이 기대한 값과 일치하는지 검증


    }

    @Test
    @DisplayName("디지털 컨테츠에 좋아요를 등록한다.")
    void likesTest() throws Exception {

        // given: 테스트 데이터 설정
        Content content = ContentFixture.DEFAULT.getContent(); // 데이터 설정
        BDDMockito.given(contentService.likes(1L)).willReturn(content); // contentService.searchContent(1L) 호출시 1L에 해당하는 가상의 Content 객체인 content를 반환하도록 설정

        // when: 컨트롤러 엔드포인트 호출
        MvcResult result = mockMvc.perform( //HTTP 요청 수행
                        MockMvcRequestBuilders.patch("/contents/{contentId}", 1L) // 요청을 생성하고 해당 URL로 patch 메소드 호출하는 시뮬레이션
                                .contentType(MediaType.APPLICATION_JSON)) // HTTP 요청 헤더에 Content-Type을 설정하여 서버에게 요청 본문이 JSON 형식임을 알림
                .andReturn(); // HTTP 요청을 싱해하고 결과를 반환


        // then: 컨트롤러 응답 검증
        mockMvc.perform(MockMvcRequestBuilders.patch("/contents/likes/{contentId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // HTTP 응답의 상태 코드가 200 (OK)인지 검증
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // 응답의 Content-Type이 JSON 형식인지 검증
                .andExpect(jsonPath("$.results").exists()) // 응답 JSON에 'results' 필드가 존재하는지 검증
                .andExpect(jsonPath("$.results.likeCnt").value(content.getLikeCnt())) // 'results' 필드 아래의 'likeCnt' 값이 기대한 값과 일치하는지 검증
                .andExpect(jsonPath("$.results.totalVoteCnt").value(content.getTotalVoteCnt())) // 'results' 필드 아래의 'totalVoteCnt' 값이 기대한 값과 일치하는지 검증
                .andExpect(jsonPath("$.results.viewCnt").value(content.getViewCnt())); // 'results' 필드 아래의 'viewCnt' 값이 기대한 값과 일치하는지 검증


    }

}
