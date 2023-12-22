package com.hositamtam.plypockets.domain.enums;

import com.hositamtam.plypockets.global.exception.HttpExceptionCode;
import com.hositamtam.plypockets.global.exception.custom.BusinessException;

import java.util.Arrays;
import java.util.Optional;

public enum DecoType {
    stripe,
    star,
    triangle,
    ribbon,
    snow,
    flower;

    public static Optional<DecoType> fromString(String decoIdx) {
        return Arrays.stream(DecoType.values())
                .filter(enumValue -> enumValue.name().equals(decoIdx))
                .findFirst();
    }

    public static DecoType safeValueOf(String decoIdx) {
        return fromString(decoIdx)
                .orElseThrow(() -> new BusinessException(HttpExceptionCode.INVALID_ARGUMENT));
    }
}
