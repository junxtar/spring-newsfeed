package com.sparta.springnewsfeed.domain.comment.exception;

import com.sparta.springnewsfeed.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {
    NOT_EXISTS_POST(HttpStatus.NOT_FOUND, "존재하지 않는 게시물입니다."),
    UNAUTHORIZED_USER(HttpStatus.FORBIDDEN, "수정 권한이 없습니다."),
    NOT_EXISTS_COMMENT(HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
