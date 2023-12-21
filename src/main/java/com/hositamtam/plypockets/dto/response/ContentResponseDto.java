package com.hositamtam.plypockets.dto.response;

import com.hositamtam.plypockets.domain.Content;
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
