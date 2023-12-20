package com.example.Playlist_pack.Dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class SpotifySearchResponseDto implements Serializable {
    private String spotifyId;
    private String artistName;
    private String title;
    private String albumName;
    private String imageUrl;
    private String previewUrl;
}
