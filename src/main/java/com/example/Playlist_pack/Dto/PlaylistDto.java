package com.example.Playlist_pack.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistDto {
    @NotNull(message = "포장지 번호는 필수 입력값입니다.")
    @Schema(description = "포장지 번호", nullable = false, example = "")
    private String coveridx;

    @NotNull(message = "장식 번호는 필수 입력값입니다.")
    @Schema(description = "장식 번호", nullable = false, example = "")
    private String decoIdx;

    @NotNull(message = "색상 번호는 필수 입력값입니다.")
    @Schema(description = "색상 번호", nullable = false, example = "")
    private String colorIdx;

    @NotNull(message = "편지 내용은 필수 입력값입니다.")
    @Schema(description = "편지 내용", nullable = false, example = "")
    private String letter;

    @NotNull(message = "발송인은 필수 입력값입니다.")
    @Schema(description = "발송인", nullable = false, example = "")
    private String friendname;

    @NotNull(message = "노래 아이디는 필수 입력값입니다.")
    @Schema(description = "노래 아이디", nullable = false, example = "")
    private Long songId;

    @NotNull(message = "플리보따리 아이디는 필수 입력값입니다.")
    @Schema(description = "플리보따리 아이디", nullable = false, example = "")
    private Long playlistPackId;
}
