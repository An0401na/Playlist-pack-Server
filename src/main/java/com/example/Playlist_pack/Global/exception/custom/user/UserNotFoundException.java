package com.example.Playlist_pack.Global.exception.custom.user;

import com.example.Playlist_pack.Global.exception.HttpExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final HttpStatus httpStatus;

    public UserNotFoundException(HttpExceptionCode httpExceptionCode) {
        super(httpExceptionCode.getMessage());
        this.httpStatus = httpExceptionCode.getHttpStatus();
    }

    public UserNotFoundException(){
        this(HttpExceptionCode.USER_NOT_FOUND);
    }

}
