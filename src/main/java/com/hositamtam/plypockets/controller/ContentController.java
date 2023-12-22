package com.hositamtam.plypockets.controller;

import com.hositamtam.plypockets.dto.response.ContentResponseDto;
import com.hositamtam.plypockets.global.dto.response.HttpResponse;
import com.hositamtam.plypockets.service.ContentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("contents")
public class ContentController {

    private final ContentService contentService;

    @PatchMapping ("/{contentId}")
    @Operation(summary = "디지털 컨텐츠 상세 조회", description = "디지털 컨텐츠 조회시 조회수, 좋아요 수, 총 투표자 수를 반환합니다. 조회수는 증가하여 반환합니다.")
    public HttpResponse<ContentResponseDto> getContentDetails(@PathVariable Long contentId){
        return HttpResponse.okBuild(ContentResponseDto.from(contentService.searchContent(contentId)));
    }


    @PatchMapping("/likes/{contentId}")
    @Operation(summary = "좋아요 등록", description = "좋아요 등록을 요청할때마다 디지털 컨텐츠의 좋아요수는 1씩 증가합니다.")
    public HttpResponse<ContentResponseDto> likes(@PathVariable Long contentId){
        return HttpResponse.okBuild(ContentResponseDto.from(contentService.likes(contentId)));
    }

}
