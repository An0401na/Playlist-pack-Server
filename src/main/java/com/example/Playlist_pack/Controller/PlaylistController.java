package com.example.Playlist_pack.Controller;

import com.example.Playlist_pack.Domain.Playlist;
import com.example.Playlist_pack.Dto.request.PlaylistRegisterRequestDto;
import com.example.Playlist_pack.Dto.response.PlaylistBoxResponseDto;
import com.example.Playlist_pack.Dto.response.PlaylistOneResponseDto;
import com.example.Playlist_pack.Dto.response.PlaylistResponseDto;
import com.example.Playlist_pack.Global.dto.response.HttpResponse;
import com.example.Playlist_pack.Service.PlaylistService;
import com.example.Playlist_pack.Service.SpotifyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/playlists")
public class PlaylistController {

    @Value("${custom.s3url}")
    private String s3url;

    private final PlaylistService playlistService;
    private final SpotifyService spotifyService;

    @PostMapping
    @Operation(summary = "선물 등록", description = "사용자가 선물을 전송합니다.")
    public HttpResponse createPlaylist(@RequestBody PlaylistRegisterRequestDto playlistRegisterRequestDto) {
        playlistService.createPlaylist(playlistRegisterRequestDto);
        return HttpResponse.createdBuilder().build();
    }

    @GetMapping
    @Operation(summary = "선물 단일 조회", description = "사용자가 받은 선물 하나를 조회합니다.")
    public HttpResponse<Optional<PlaylistOneResponseDto>> getPlaylistOne(@RequestParam Long userId, @RequestParam Long playlistId) {
        return HttpResponse.okBuild(
                playlistService.searchPlayListOne(userId, playlistId)
                        .map(PlaylistOneResponseDto::from));
    }


    @GetMapping("/boxes")
    @Operation(summary = "선물 상자 전체 조회", description = "사용자가 받은 선물 상자에 대한 정보만 조회합니다.")
    public HttpResponse<List<PlaylistBoxResponseDto>> getPlayListPackBoxes(@RequestParam String nickname){
        return HttpResponse.okBuild(
                playlistService.searchPlayListPack(nickname)
                        .stream()
                        .map(playlist -> PlaylistBoxResponseDto.of(playlist,s3url))
                        .toList());
    }


    @GetMapping("/{userId}")
    @Operation(summary = "선물 상자 전체 조회", description = "사용자가 받은 선물 상자에 대한 정보만 조회합니다.")
    public HttpResponse<List<PlaylistResponseDto>> getPlayListPack(@PathVariable Long userId){
        return HttpResponse.okBuild(
                playlistService.searchPlayListPack(userId)
                        .stream()
                        .map((Playlist playlist) -> PlaylistResponseDto.of(playlist, spotifyService.getTrackBySpotifyId(playlist.getSpotifyId()), s3url ))
                        .toList());
    }
}