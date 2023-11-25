package com.sparta.springnewsfeed.domain.user.exception;

import com.sparta.springnewsfeed.global.exception.*;

public class NonUserExsistException extends RestApiException {

    public NonUserExsistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
