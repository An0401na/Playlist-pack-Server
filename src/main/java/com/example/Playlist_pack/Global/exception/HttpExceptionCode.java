package com.example.Playlist_pack.Global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum HttpExceptionCode {
    UNEXPECTED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR,  "예상치 못한 오류가 발생했습니다."),
    INVALID_ARGUMENT(HttpStatus.BAD_REQUEST,  "올바르지 않은 값이 전달되었습니다."),
    REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "요청을 찾을 수 없습니다."),
    INTERNAL_SERVER_EXCEPTION(
            HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 오류가 발생했습니다."),
    PLAYLISTPACK_NOT_FOUND(HttpStatus.NOT_FOUND, "유저의 플리보따리를 찾을 수 없습니다."),
    CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 컨텐츠를 찾을 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 사용자를 찾을 수 없습니다."),
    USER_ALREADY_VOTED(HttpStatus.BAD_REQUEST, "요청한 사용자는 이미 투표에 참여하였습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}