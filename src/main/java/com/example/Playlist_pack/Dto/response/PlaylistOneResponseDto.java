package com.example.Playlist_pack.Dto.response;

import com.example.Playlist_pack.Domain.Playlist;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
public record PlaylistOneResponseDto(Long playlistId,
                                     String spotifyId,
                                     String friendname,
                                     String letter,
                                     LocalDateTime createdDate){
    public static PlaylistOneResponseDto from(Playlist playlist){
        return PlaylistOneResponseDto.builder()
                .playlistId(playlist.getPlaylistId())
                .spotifyId(playlist.getSpotifyId())
                .friendname(playlist.getFriendname())
                .letter(playlist.getLetter())
                .createdDate(playlist.getCreatedDate())
                .build();

    }



}