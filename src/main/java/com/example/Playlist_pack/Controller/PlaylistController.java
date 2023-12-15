package com.example.Playlist_pack.Controller;

import com.example.Playlist_pack.Domain.Playlist;
import com.example.Playlist_pack.Dto.request.PlaylistRegisterRequestDto;
import com.example.Playlist_pack.Dto.response.PlaylistOneResponseDto;
import com.example.Playlist_pack.Dto.response.PlaylistResponseDto;
import com.example.Playlist_pack.Global.dto.response.HttpResponse;
import com.example.Playlist_pack.Service.PlaylistService;
import com.example.Playlist_pack.Service.SpotifyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;
    private final SpotifyService spotifyService;

    @PostMapping
    @Operation(summary = "선물 등록", description = "사용자가 선물을 전송합니다.")
    public HttpResponse createPlaylist(@RequestBody PlaylistRegisterRequestDto playlistRegisterRequestDto) {
        playlistService.createPlaylist(playlistRegisterRequestDto);
        return HttpResponse.createdBuilder().build();
    }

    @GetMapping("/{userId}/{playlistId}")
    @Operation(summary = "선물(플리) (하나) 조회", description = "사용자가 받은 선물 하나를 조회합니다.")
    public HttpResponse<Optional<PlaylistOneResponseDto>> getPlaylistOne(@PathVariable Long userId, @PathVariable Long playlistId) {
        return HttpResponse.okBuild(
                playlistService.searchPlayListOne(userId, playlistId)
                        .map(PlaylistOneResponseDto::from));
    }


    @GetMapping("/{userId}")
    @Operation(summary = "플리보따리 조회 (선물 전체 조회)", description = "사용자가 받은 선물 상자를 전체 조회합니다.")
    public HttpResponse<List<PlaylistResponseDto>> getPlayListPack(@PathVariable Long userId){
        return HttpResponse.okBuild(
                playlistService.searchPlayListPack(userId)
                        .stream()
                        .map((Playlist playlist) -> PlaylistResponseDto.of(playlist, spotifyService.getTrackBySpotifyId(playlist.getSpotifyId()) ))
                        .toList());
    }
}