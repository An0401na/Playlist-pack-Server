package com.hositamtam.plypockets.dto.request;

import com.hositamtam.plypockets.domain.Playlist;
import com.hositamtam.plypockets.domain.User;
import com.hositamtam.plypockets.domain.enums.ColorType;
import com.hositamtam.plypockets.domain.enums.CoverType;
import com.hositamtam.plypockets.domain.enums.DecoType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;


public record PlaylistRegisterRequestDto (


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

    @Schema(description = "스포티파이 노래 아이디", nullable = true, example = "")
    String spotifyId  ) {

    public Playlist toEntity(User user){
        return Playlist.builder()
                .coverIdx(CoverType.safeValueOf(coverIdx))
                .decoIdx(DecoType.safeValueOf(decoIdx))
                .colorIdx(ColorType.safeValueOf(colorIdx))
                .letter(letter)
                .friendname(friendname)
                .spotifyId(spotifyId)
                .user(user)
                .build();
    }

}
