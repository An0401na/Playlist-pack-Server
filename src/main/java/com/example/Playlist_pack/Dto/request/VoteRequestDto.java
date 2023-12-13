package com.example.Playlist_pack.Dto.request;


import com.example.Playlist_pack.Domain.User;
import com.example.Playlist_pack.Domain.Vote;
import com.example.Playlist_pack.Domain.VoteLog;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record VoteRequestDto(
        @NotNull
        @Schema(description = "컨텐츠 아이디", nullable = false)
        Long contentId,
        @NotNull
        @Schema(description = "사용자 아이디", nullable = false)
        Long userId,
        @NotNull
        @Schema(description = "투표 선택지 번호", nullable = false)
        Integer choice) {

    public VoteLog toEntity(Vote vote, User user) {
        return VoteLog.builder()
                .vote(vote)
                .user(user)
                .build();
    }
}
