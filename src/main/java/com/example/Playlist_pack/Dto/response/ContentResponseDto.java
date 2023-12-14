package com.example.Playlist_pack.Dto.response;

import com.example.Playlist_pack.Domain.Content;
import lombok.Builder;

@Builder
public record ContentResponseDto(Long viewCnt,
                                 Long likeCnt,
                                 Long totalVoteCnt) {
    public static ContentResponseDto from(Content content){
        return ContentResponseDto.builder()
                .viewCnt(content.getViewCnt())
                .likeCnt(content.getLikeCnt())
                .totalVoteCnt(content.getTotalVoteCnt())
                .build();

    }
}
