package com.example.Playlist_pack.Controller;

import com.example.Playlist_pack.Dto.request.PlaylistRegisterRequestDto;
import com.example.Playlist_pack.Global.dto.response.HttpResponse;
import com.example.Playlist_pack.Service.PlaylistService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping("/{userid}")
    @Operation(summary = "선물 등록", description = "사용자가 선물을 전송합니다.")
    public HttpResponse createPlaylist(@PathVariable("userid") String userId, @RequestBody PlaylistRegisterRequestDto playlistRegisterRequestDto) {
        playlistService.createPlaylist(playlistRegisterRequestDto);
        return HttpResponse.createdBuilder().build();
    }
}