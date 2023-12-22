package com.hositamtam.plypockets.global.dto.response;

import org.springframework.http.HttpStatus;

public record ErrorResponseDto(HttpStatus errorCode, String message) {


    public static ErrorResponseDto from(HttpStatus exceptionCode, String errorMessage) {
        return new ErrorResponseDto(exceptionCode, errorMessage);
    }
}
