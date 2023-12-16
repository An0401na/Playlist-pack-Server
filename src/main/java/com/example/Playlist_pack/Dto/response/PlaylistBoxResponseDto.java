package com.example.Playlist_pack.Dto.response;

import com.example.Playlist_pack.Domain.Playlist;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PlaylistBoxResponseDto(Long playlistId,
                                  String boxImageUrl,
                                  LocalDateTime createdDate ) {
    public static PlaylistBoxResponseDto from(Playlist playlist){
        return PlaylistBoxResponseDto.builder()
                .playlistId(playlist.getPlaylistId())
                .boxImageUrl(playlist.getCoverIdx()+"_"+playlist.getDecoIdx()+"_"+playlist.getColorIdx())
                .createdDate(playlist.getCreatedDate())
                .build();

    }
}
