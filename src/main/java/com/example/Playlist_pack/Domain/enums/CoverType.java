package com.example.Playlist_pack.Domain.enums;

import com.example.Playlist_pack.Global.exception.HttpExceptionCode;
import com.example.Playlist_pack.Global.exception.custom.BusinessException;

import java.util.Arrays;
import java.util.Optional;

public enum CoverType {
    red,
    green,
    pink;

    public static Optional<CoverType> fromString(String coverIdx) {
        return Arrays.stream(CoverType.values())
                .filter(enumValue -> enumValue.name().equals(coverIdx))
                .findFirst();
    }

    public static CoverType safeValueOf(String coverIdx) {
        return fromString(coverIdx)
                .orElseThrow(() -> new BusinessException(HttpExceptionCode.INVALID_ARGUMENT));
    }

}
