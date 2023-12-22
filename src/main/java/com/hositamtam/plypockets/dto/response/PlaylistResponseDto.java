package com.hositamtam.plypockets.dto.response;

import com.hositamtam.plypockets.domain.Playlist;
import com.hositamtam.plypockets.dto.SpotifySearchResponseDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PlaylistResponseDto(Long playlistId,
                                  String boxImageUrl,
                                  String title,
                                  String artistName,
                                  String imageUrl,
                                  String previewUrl,
                                  String friendname,
                                  String letter,
                                  LocalDateTime createdDate ) {
    public static PlaylistResponseDto of(Playlist playlist, SpotifySearchResponseDto spotifySearchResponseDto, String  s3url){
        return PlaylistResponseDto.builder()
                .playlistId(playlist.getPlaylistId())
                .boxImageUrl(s3url+"gift-box/"+playlist.getCoverIdx()+"_"+playlist.getDecoIdx()+"_"+playlist.getColorIdx()+".jpg")
                .title(spotifySearchResponseDto.getTitle())
                .artistName(spotifySearchResponseDto.getArtistName())
                .imageUrl(spotifySearchResponseDto.getImageUrl())
                .previewUrl(spotifySearchResponseDto.getPreviewUrl())
                .friendname(playlist.getFriendname())
                .letter(playlist.getLetter())
                .createdDate(playlist.getCreatedDate())
                .build();

    }

    public static PlaylistResponseDto of(Playlist playlist, String  s3url){
        return PlaylistResponseDto.builder()
                .playlistId(playlist.getPlaylistId())
                .boxImageUrl(s3url+"gift-box/"+playlist.getCoverIdx()+"_"+playlist.getDecoIdx()+"_"+playlist.getColorIdx()+".jpg")
                .title(null)
                .artistName(null)
                .imageUrl(null)
                .previewUrl(null)
                .friendname(playlist.getFriendname())
                .letter(playlist.getLetter())
                .createdDate(playlist.getCreatedDate())
                .build();

    }
}
