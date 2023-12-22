package com.hositamtam.plypockets.domain.enums;

import com.hositamtam.plypockets.global.exception.HttpExceptionCode;
import com.hositamtam.plypockets.global.exception.custom.BusinessException;

import java.util.Arrays;
import java.util.Optional;

public enum ColorType {
    coral,
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
