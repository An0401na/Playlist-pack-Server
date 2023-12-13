package com.example.Playlist_pack.Controller;

import com.example.Playlist_pack.Dto.response.VoteLogResponseDto;
import com.example.Playlist_pack.Global.dto.response.HttpResponse;
import com.example.Playlist_pack.Service.VoteLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("votes")
public class VoteLogController {

    private final VoteLogService voteLogService;

    @GetMapping("/{contentId}/{userId}")
    public HttpResponse<VoteLogResponseDto> getVoteDetils(@PathVariable Long contentId, @PathVariable Long userId){
        return HttpResponse.okBuild(voteLogService.getVoteDetils(contentId, userId));
    }
}
