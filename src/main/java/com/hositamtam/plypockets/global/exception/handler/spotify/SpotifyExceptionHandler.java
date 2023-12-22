package com.hositamtam.plypockets.global.exception.handler.spotify;

import com.hositamtam.plypockets.global.dto.response.ErrorResponseDto;
import com.hositamtam.plypockets.global.dto.response.HttpResponse;
import com.hositamtam.plypockets.global.exception.custom.spotify.SpotifyErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpotifyExceptionHandler {
    @ExceptionHandler(SpotifyErrorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpResponse<ErrorResponseDto> spotifyExceptionHandler(SpotifyErrorException e) {
        log.warn("SpotifyErrorException: {}", e);
        return HttpResponse.status(e.getHttpStatus())
                .body(ErrorResponseDto.from(e.getHttpStatus(), e.getMessage()));
    }
}
