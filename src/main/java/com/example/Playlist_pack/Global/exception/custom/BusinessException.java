package com.example.Playlist_pack.Global.exception.custom;

import com.example.Playlist_pack.Global.exception.HttpExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    private final HttpStatus httpStatus;

    public BusinessException(HttpExceptionCode httpExceptionCode) {
        super(httpExceptionCode.getMessage());
        this.httpStatus = httpExceptionCode.getHttpStatus();
    }
}