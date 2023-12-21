package com.hositamtam.plypockets.global.exception.custom.spotify;

import com.hositamtam.plypockets.global.exception.HttpExceptionCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter
@Setter
public class SpotifyErrorException extends RuntimeException {
    private final HttpStatus httpStatus;

    public SpotifyErrorException(HttpExceptionCode httpExceptionCode) {
        super(httpExceptionCode.getMessage());
        this.httpStatus = httpExceptionCode.getHttpStatus();
    }

    public SpotifyErrorException() {
        this(HttpExceptionCode.SPOTIFYSONG_NOT_FOUND);
    }

}
