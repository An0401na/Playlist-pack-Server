package com.example.Playlist_pack.Dto.response;

import com.example.Playlist_pack.Domain.Playlist;
import com.example.Playlist_pack.Dto.SpotifySearchResponseDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PlaylistResponseDto(Long playlistId,
                                  String imageName,
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
                .imageName(playlist.getCoverIdx()+"_"+playlist.getDecoIdx()+"_"+playlist.getColorIdx())
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
