package com.example.Playlist_pack.Dto.response;

import com.example.Playlist_pack.Domain.Playlist;
import com.example.Playlist_pack.Dto.SpotifySearchResponseDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PlaylistResponseDto(Long playlistId,
                                  String coverIdx,
                                  String decoIdx,
                                  String colorIdx,
                                  String title,
                                  String artistName,
                                  String imageUrl,
                                  String previewUrl,
                                  String friendname,
                                  String letter,
                                  LocalDateTime createdDate ) {
    public static PlaylistResponseDto of(Playlist playlist, SpotifySearchResponseDto spotifySearchResponseDto){
        return PlaylistResponseDto.builder()
                .playlistId(playlist.getPlaylistId())
                .coverIdx(playlist.getCoverIdx())
                .decoIdx(playlist.getDecoIdx())
                .colorIdx(playlist.getColorIdx())
                .title(spotifySearchResponseDto.getTitle())
                .artistName(spotifySearchResponseDto.getArtistName())
                .imageUrl(spotifySearchResponseDto.getImageUrl())
                .previewUrl(spotifySearchResponseDto.getPreviewUrl())
                .friendname(playlist.getFriendname())
                .letter(playlist.getLetter())
                .createdDate(playlist.getCreatedDate())
                .build();

    }
}
