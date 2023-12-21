package com.example.Playlist_pack.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SpotifyErrorDto implements SpotifyDto {
    String message;
}

