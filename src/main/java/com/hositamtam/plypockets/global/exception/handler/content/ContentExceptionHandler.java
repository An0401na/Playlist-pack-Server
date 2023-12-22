package com.hositamtam.plypockets.global.exception.handler.content;

import com.hositamtam.plypockets.global.dto.response.ErrorResponseDto;
import com.hositamtam.plypockets.global.dto.response.HttpResponse;
import com.hositamtam.plypockets.global.exception.custom.content.ContentNotFoundException;
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
    public HttpResponse<ErrorResponseDto> contentNotFoundExceptionHandler(ContentNotFoundException e) {
        log.warn("contentNotFoundException : {}", e);
        return HttpResponse.status(e.getHttpStatus())
                .body(ErrorResponseDto.from(e.getHttpStatus(), e.getMessage()));
    }

}
