package com.hositamtam.plypockets.global.exception.custom.content;

import com.hositamtam.plypockets.global.exception.HttpExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ContentNotFoundException  extends RuntimeException {
    private final HttpStatus httpStatus;

    public ContentNotFoundException(HttpExceptionCode httpExceptionCode) {
        super(httpExceptionCode.getMessage());
        this.httpStatus = httpExceptionCode.getHttpStatus();
    }

    public ContentNotFoundException(){
        this(HttpExceptionCode.CONTENT_NOT_FOUND);
    }

}
