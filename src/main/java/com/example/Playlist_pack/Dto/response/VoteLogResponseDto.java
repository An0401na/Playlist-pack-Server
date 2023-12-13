package com.example.Playlist_pack.Dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record VoteLogResponseDto(Boolean hasVoted,
                                 Integer choice,
                                 List<VoteResultResponseDto> voteResultResponseDtoList) {
    public static VoteLogResponseDto of(Boolean hasVoted, Integer choice, List<VoteResultResponseDto> voteResultResponseDtoList){
        return VoteLogResponseDto.builder()
                .hasVoted(hasVoted)
                .choice(choice)
                .voteResultResponseDtoList(voteResultResponseDtoList)
                .build();

    }
}
