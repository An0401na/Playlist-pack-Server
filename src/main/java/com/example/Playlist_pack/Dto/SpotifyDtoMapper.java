package com.example.Playlist_pack.Dto;

public class SpotifyDtoMapper {
    public static SpotifySearchResponseDto toSearchDto(String artistName, String title, String albumName, String imageUrl, String previewUrl) {
        return  SpotifySearchResponseDto.builder()
                .artistName(artistName)
                .title(title)
                .albumName(albumName)
                .imageUrl(imageUrl)
                .previewUrl(previewUrl)
                .build();
    }
}
