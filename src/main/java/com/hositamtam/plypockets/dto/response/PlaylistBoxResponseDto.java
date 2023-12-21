package com.hositamtam.plypockets.dto.response;

import com.hositamtam.plypockets.domain.Playlist;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PlaylistBoxResponseDto(Long playlistId,
                                  String boxImageUrl,
                                  LocalDateTime createdDate ) {

    public static PlaylistBoxResponseDto of(Playlist playlist, String s3url){
        return PlaylistBoxResponseDto.builder()
                .playlistId(playlist.getPlaylistId())
                .boxImageUrl(s3url+"gift-box/"+playlist.getCoverIdx()+"_"+playlist.getDecoIdx()+"_"+playlist.getColorIdx()+".jpg")
                .createdDate(playlist.getCreatedDate())
                .build();

    }
}
