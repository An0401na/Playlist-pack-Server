package com.example.Playlist_pack.Dto.response;

import com.example.Playlist_pack.Domain.Playlist;
import lombok.Builder;

@Builder
public record PlaylistResponseDto(Long playlistId,
                                  String coverIdx,
                                  String decoIdx,
                                  String colorIdx,
                                  String spotifyId) {
    public static PlaylistResponseDto from(Playlist playlist){
        return PlaylistResponseDto.builder()
                .playlistId(playlist.getPlaylistId())
                .coverIdx(playlist.getCoverIdx())
                .decoIdx(playlist.getDecoIdx())
                .colorIdx(playlist.getColorIdx())
                .spotifyId(playlist.getSpotifyId())
                .build();

    }
}
