package com.sparta.springnewsfeed.domain.user.exception;

import com.sparta.springnewsfeed.global.exception.*;
import lombok.*;
import org.springframework.http.*;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    REJECTED_USER_EXECUTION(HttpStatus.FORBIDDEN, "작성자만 수정 할 수 있어요!"),
    NON_PROFILE_EXSIST(HttpStatus.NOT_FOUND, "프로필이 존재하지 않아요!"),
    ALREADY_EXSIST_USER(HttpStatus.FORBIDDEN, "이미 존재하는 사용자에요!");
    private final HttpStatus httpStatus;
    private final String message;
}