package com.example.Playlist_pack.Controller;

import com.example.Playlist_pack.Dto.response.ContentRespnseDto;
import com.example.Playlist_pack.Global.dto.response.HttpResponse;
import com.example.Playlist_pack.Service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("contents")
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/{contentId}")
    public HttpResponse<ContentRespnseDto> getContentDetails(@PathVariable Long contentId){
        return HttpResponse.okBuild(ContentRespnseDto.from(contentService.searchContent(contentId)));
    }

}
