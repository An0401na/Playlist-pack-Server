package com.example.Playlist_pack.Controller;

import com.example.Playlist_pack.Dto.response.ContentRespnseDto;
import com.example.Playlist_pack.Global.dto.response.HttpResponse;
import com.example.Playlist_pack.Service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("contents")
public class ContentController {

    private final ContentService contentService;

    @PatchMapping ("/{contentId}")
    public HttpResponse<ContentRespnseDto> getContentDetails(@PathVariable Long contentId){
        return HttpResponse.okBuild(ContentRespnseDto.from(contentService.searchContent(contentId)));
    }


    @PatchMapping("/likes/{contentId}")
    public HttpResponse<ContentRespnseDto> likes(@PathVariable Long contentId){
        return HttpResponse.okBuild(ContentRespnseDto.from(contentService.likes(contentId)));
    }

}
