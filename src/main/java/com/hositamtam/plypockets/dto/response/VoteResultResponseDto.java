package com.hositamtam.plypockets.dto.response;

import lombok.Builder;

@Builder
public record VoteResultResponseDto(Integer choice,
                                    Integer percent) {
    public static VoteResultResponseDto of(Integer choice, Integer percent){
        return VoteResultResponseDto.builder()
                .choice(choice)
                .percent(percent)
                .build();
    }
}
