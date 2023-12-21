package com.hositamtam.plypockets.dto;

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

    public static SpotifyErrorDto toErrorDto(String message) {
        return SpotifyErrorDto.builder()
                .message(message)
                .build();
    }
}
