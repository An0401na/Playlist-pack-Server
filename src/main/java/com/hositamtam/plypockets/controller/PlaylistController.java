package com.hositamtam.plypockets.controller;

import com.hositamtam.plypockets.domain.Playlist;
import com.hositamtam.plypockets.dto.request.PlaylistRegisterRequestDto;
import com.hositamtam.plypockets.dto.response.PlaylistBoxResponseDto;
import com.hositamtam.plypockets.dto.response.PlaylistOneResponseDto;
import com.hositamtam.plypockets.dto.response.PlaylistResponseDto;
import com.hositamtam.plypockets.global.dto.response.HttpResponse;
import com.hositamtam.plypockets.service.PlaylistService;
import com.hositamtam.plypockets.service.SpotifyService;
import com.mysql.cj.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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

    @PostMapping("/{nickname}")
    @Operation(summary = "선물 등록", description = "사용자가 선물을 전송합니다.")
    public HttpResponse createPlaylist(@Valid @RequestBody PlaylistRegisterRequestDto playlistRegisterRequestDto, @PathVariable String nickname) {
        playlistService.createPlaylist(playlistRegisterRequestDto, nickname);
        return HttpResponse.createdBuilder().build();
    }

    @GetMapping
    @Operation(summary = "선물 단일 조회", description = "사용자가 받은 선물 하나를 조회합니다.")
    public HttpResponse<Optional<PlaylistOneResponseDto>> getPlaylistOne(@RequestParam Long userId, @RequestParam Long playlistId) {
        return HttpResponse.okBuild(
                playlistService.searchPlayListOne(userId, playlistId)
                        .map((Playlist playlist) -> PlaylistOneResponseDto.from(playlist, s3url)));
    }



    @GetMapping("/boxes")
    @Operation(summary = "선물 상자 포장 전체 조회", description = "사용자가 받은 선물 상자에 대한 정보만 조회합니다.")
    public HttpResponse<List<PlaylistBoxResponseDto>> getPlayListPackBoxes(@RequestParam String nickname){
        return HttpResponse.okBuild(
                playlistService.searchPlayListPackByNickname(nickname)
                        .stream()
                        .map(playlist -> PlaylistBoxResponseDto.of(playlist,s3url))
                        .toList());
    }


    @GetMapping("/{userId}")
    @Operation(summary = "선물 상자 전체 조회", description = "사용자가 받은 선물 상자에 대한 정보와 상자 내용과 함께 조회합니다.")
    public HttpResponse<List<PlaylistResponseDto>> getPlayListPack(@PathVariable Long userId){
        return HttpResponse.okBuild(
                playlistService.searchPlayListPackByUserId(userId)
                        .stream()
                        .map((playlist) -> StringUtils.isNullOrEmpty(playlist.getSpotifyId()) ?
                                        PlaylistResponseDto.of(playlist, s3url) :
                                        PlaylistResponseDto.of(playlist,
                                                 spotifyService.getTrackBySpotifyId(playlist.getSpotifyId()), s3url))
                        .toList());
    }


}