package com.sparta.springnewsfeed.domain.post.exception;

import com.sparta.springnewsfeed.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostErrorCode implements ErrorCode {
    NOT_EXISTS_POST(HttpStatus.NOT_FOUND, "존재하지 않는 게시물 입니다."),
    NOT_PERMISSION(HttpStatus.FORBIDDEN, "해당 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
