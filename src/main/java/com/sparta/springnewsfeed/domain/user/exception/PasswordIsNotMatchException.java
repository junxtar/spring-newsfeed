package com.sparta.springnewsfeed.domain.user.exception;

import com.sparta.springnewsfeed.global.exception.*;

public class PasswordIsNotMatchException extends RestApiException {

    public PasswordIsNotMatchException(ErrorCode errorCode) {
        super(errorCode);
    }
}
