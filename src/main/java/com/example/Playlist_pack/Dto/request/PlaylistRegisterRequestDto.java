package com.example.Playlist_pack.Dto.request;

import com.example.Playlist_pack.Domain.Playlist;
import com.example.Playlist_pack.Domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;


public record PlaylistRegisterRequestDto (

    @NotNull(message = "수신인 아이디는 필수 입력값입니다.")
    @Schema(description = "수신인", nullable = false, example = "")
    Long userId,

    @NotNull(message = "포장지 번호는 필수 입력값입니다.")
    @Schema(description = "포장지 번호", nullable = false, example = "")
    String coverIdx,

    @NotNull(message = "장식 번호는 필수 입력값입니다.")
    @Schema(description = "장식 번호", nullable = false, example = "")
    String decoIdx,

    @NotNull(message = "색상 번호는 필수 입력값입니다.")
    @Schema(description = "색상 번호", nullable = false, example = "")
    String colorIdx,

    @NotNull(message = "편지 내용은 필수 입력값입니다.")
    @Schema(description = "편지 내용", nullable = false, example = "")
    String letter,

    @NotNull(message = "발송인은 필수 입력값입니다.")
    @Schema(description = "발송인", nullable = false, example = "")
    String friendname,

    @NotNull(message = "스포티파이 노래 아이디는 필수 입력값입니다.")
    @Schema(description = "스포티파이 노래 아이디", nullable = false, example = "")
    String spotifyId  ) {

    public Playlist toEntity(User user){
        return Playlist.builder()
                .coverIdx(coverIdx)
                .decoIdx(decoIdx)
                .colorIdx(colorIdx)
                .letter(letter)
                .friendname(friendname)
                .spotifyId(spotifyId)
                .user(user)
                .build();
    }

}
