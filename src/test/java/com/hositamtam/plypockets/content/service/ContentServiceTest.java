package com.hositamtam.plypockets.content.service;

import com.hositamtam.plypockets.content.ContentFixture;
import com.hositamtam.plypockets.domain.Content;
import com.hositamtam.plypockets.global.exception.custom.content.ContentNotFoundException;
import com.hositamtam.plypockets.repository.ContentRepository;
import com.hositamtam.plypockets.service.ContentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ContentServiceTest {

    @Mock ContentRepository contentRepository;

    @InjectMocks ContentService contentService;

    @Test
    @DisplayName("searchContent 메서드 성공 테스트 : contentId로 Content 조회")
    void searchContentSuccess(){
        // given
        Content content = ContentFixture.DEFAULT.getContent();
        BDDMockito.given(contentRepository.findById(any(Long.class)))
                .willReturn(Optional.ofNullable(content));

        // when
        Content result = contentService.searchContent(1L);


        // then
        assertEquals(1L, result.getContentId());
        assertEquals(101L, result.getViewCnt()); // updateViewCnt() 메서드를 호출하여 viewCnt가 101로 업데이트되었는지 확인
        assertEquals(100L, result.getLikeCnt()); // 초기 값은 100으로 설정되어 있으므로 100인지 확인
        assertEquals(10L, result.getTotalVoteCnt()); // 초기 값은 10으로 설정되어 있으므로 10인지 확인

    }

    @Test
    @DisplayName("searchContent 메서드 실패 테스트 : contentId로 Content 조회 실패")
    void searchContentFailure() {
        // given
        BDDMockito.given(contentRepository.findById(any(Long.class)))
                .willReturn(Optional.empty()); //찾지 못한 경우이므로 empty

        // when and then
        assertThrows(ContentNotFoundException.class, () -> contentService.searchContent(1L)); //contentService.searchContent(1L) 메서드 호출이 ContentNotFoundException을 발생시켜야 한다
    }


    @Test
    @DisplayName("likes 메서드 성공 테스트 : 좋아요 등록")
    void likesSuccess(){
        // given
        Content content = ContentFixture.DEFAULT.getContent();
        BDDMockito.given(contentRepository.findById(any(Long.class)))
                .willReturn(Optional.ofNullable(content));

        // when
        Content result = contentService.likes(1L);


        // then
        assertEquals(1L, result.getContentId());
        assertEquals(100L, result.getViewCnt()); // 초기 값은 100으로 설정되어 있으므로 100인지 확인
        assertEquals(101L, result.getLikeCnt()); // updateLikeCnt() 메서드를 호출하여 viewCnt가 101로 업데이트되었는지 확인
        assertEquals(10L, result.getTotalVoteCnt()); // 초기 값은 10으로 설정되어 있으므로 10인지 확인

    }

    @Test
    @DisplayName("likes 메서드 실패 테스트 : contentId로 Content 조회 실패")
    void likesFailure() {
        // given
        BDDMockito.given(contentRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        // when and then
        assertThrows(ContentNotFoundException.class, () -> contentService.likes(1L));
    }


}
