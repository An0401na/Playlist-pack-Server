package com.example.Playlist_pack.Dto.request;


import com.example.Playlist_pack.Domain.User;
import com.example.Playlist_pack.Domain.Vote;
import com.example.Playlist_pack.Domain.VoteLog;
import jakarta.validation.constraints.NotNull;

public record VoteRequestDto(
        @NotNull Long contentId,
        @NotNull Long userId,
        @NotNull Integer choice) {

    public VoteLog toEntity(Vote vote, User user) {
        return VoteLog.builder()
                .vote(vote)
                .user(user)
                .build();
    }
}
