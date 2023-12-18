package com.example.Playlist_pack.Dto.response;

import com.example.Playlist_pack.Domain.Playlist;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PlaylistOneResponseDto(Long playlistId,
                                     String boxImageUrl,
                                     String spotifyId,
                                     String friendname,
                                     String letter,
                                     LocalDateTime createdDate){
    public static PlaylistOneResponseDto from(Playlist playlist, String s3url){
        return PlaylistOneResponseDto.builder()
                .playlistId(playlist.getPlaylistId())
                .boxImageUrl(s3url+"gift-box/"+playlist.getCoverIdx()+"_"+playlist.getDecoIdx()+"_"+playlist.getColorIdx()+".jpg")
                .spotifyId(playlist.getSpotifyId())
                .friendname(playlist.getFriendname())
                .letter(playlist.getLetter())
                .createdDate(playlist.getCreatedDate())
                .build();

    }



}