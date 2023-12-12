package com.example.Playlist_pack.Global.exception.handler.content;

import com.example.Playlist_pack.Global.dto.response.ErrorResponseDto;
import com.example.Playlist_pack.Global.dto.response.HttpResponse;
import com.example.Playlist_pack.Global.exception.custom.content.ContentNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ContentExceptionHandler {
    @ExceptionHandler(ContentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpResponse<ErrorResponseDto> contentExceptionHandler(ContentNotFoundException e) {
        log.warn("contentNotFoundException : {}", e);
        return HttpResponse.status(e.getHttpStatus())
                .body(ErrorResponseDto.from(e.getHttpStatus(), e.getMessage()));
    }
}
