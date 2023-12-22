package com.hositamtam.plypockets.global.exception.custom.vote;

import com.hositamtam.plypockets.global.exception.HttpExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserAlreadyVotedException extends RuntimeException {
    private final HttpStatus httpStatus;

    public UserAlreadyVotedException(HttpExceptionCode httpExceptionCode) {
        super(httpExceptionCode.getMessage());
        this.httpStatus = httpExceptionCode.getHttpStatus();
    }

    public UserAlreadyVotedException(){
        this(HttpExceptionCode.USER_ALREADY_VOTED);
    }

}
