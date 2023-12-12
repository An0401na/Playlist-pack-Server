package com.example.Playlist_pack.Global.exception;

import com.example.Playlist_pack.Global.dto.response.ErrorResponseDto;
import com.example.Playlist_pack.Global.exception.custom.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDto> businessExceptionHandle(BusinessException e) {
        log.warn("businessException : {}", e);
        return ResponseEntity.internalServerError()
                .body(ErrorResponseDto.from(e.getHttpStatus(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> allUncaughtHandle(Exception e) {
        log.error("allUncaughtHandle : {}", e);
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponseDto> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidException : {}", e);
        return ResponseEntity.badRequest()
                .body(ErrorResponseDto.from(HttpStatus.BAD_REQUEST, e.getFieldErrors().get(0).getDefaultMessage()));
    }
}