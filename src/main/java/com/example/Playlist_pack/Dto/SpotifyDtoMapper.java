package com.example.Playlist_pack.Dto;

public class SpotifyDtoMapper {
    public static SpotifySearchResponseDto toSearchDto(String spotifyId,String artistName, String title, String albumName, String imageUrl, String previewUrl) {
        return  SpotifySearchResponseDto.builder()
                .spotifyId(spotifyId)
                .artistName(artistName)
                .title(title)
                .albumName(albumName)
                .imageUrl(imageUrl)
                .previewUrl(previewUrl)
                .build();
    }
}
