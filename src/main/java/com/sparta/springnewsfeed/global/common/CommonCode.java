package com.sparta.springnewsfeed.global.common;

import lombok.*;
import org.springframework.http.*;

@Getter
public enum CommonCode {
    OK("회원 가입을 완료했습니다!", HttpStatus.OK.value());
    private final String message;
    private final Integer code;

    CommonCode(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
