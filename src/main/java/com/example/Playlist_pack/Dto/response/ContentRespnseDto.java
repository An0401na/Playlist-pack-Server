package com.example.Playlist_pack.Dto.response;

import com.example.Playlist_pack.Domain.Content;
import lombok.Builder;

@Builder
public record ContentRespnseDto(Long viewCnt,
                                Long likeCnt,
                                Long totalVoteCnt) {
    public static ContentRespnseDto from(Content content){
        return ContentRespnseDto.builder()
                .viewCnt(content.getViewCnt())
                .likeCnt(content.getLikeCnt())
                .totalVoteCnt(content.getTotalVoteCnt())
                .build();

    }
}
