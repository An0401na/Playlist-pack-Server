package com.hositamtam.plypockets.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record VoteLogResponseDto(Boolean hasVoted,
                                 Integer choice,
                                 Long totalVoteCnt,
                                 List<VoteResultResponseDto> voteResultResponseDtoList) {
    public static VoteLogResponseDto of(Boolean hasVoted, Integer choice,Long totalVoteCnt, List<VoteResultResponseDto> voteResultResponseDtoList){
        return VoteLogResponseDto.builder()
                .hasVoted(hasVoted)
                .choice(choice)
                .totalVoteCnt(totalVoteCnt)
                .voteResultResponseDtoList(voteResultResponseDtoList)
                .build();

    }
}
