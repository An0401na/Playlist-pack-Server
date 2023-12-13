package com.example.Playlist_pack.Global.exception.custom.vote;

import com.example.Playlist_pack.Global.exception.HttpExceptionCode;
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
