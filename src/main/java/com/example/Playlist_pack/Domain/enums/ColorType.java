package com.example.Playlist_pack.Domain.enums;

import com.example.Playlist_pack.Global.exception.HttpExceptionCode;
import com.example.Playlist_pack.Global.exception.custom.BusinessException;

import java.util.Arrays;
import java.util.Optional;

public enum ColorType {
    colar,
    mint,
    white;

    public static Optional<ColorType> fromString(String colorIdx) {
        return Arrays.stream(ColorType.values())
                .filter(enumValue -> enumValue.name().equals(colorIdx))
                .findFirst();
    }

    public static ColorType safeValueOf(String colorIdx) {
        return fromString(colorIdx)
                .orElseThrow(() -> new BusinessException(HttpExceptionCode.INVALID_ARGUMENT));
    }
}
