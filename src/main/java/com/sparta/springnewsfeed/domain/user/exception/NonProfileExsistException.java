package com.sparta.springnewsfeed.domain.user.exception;

import com.sparta.springnewsfeed.global.exception.*;

public class NonProfileExsistException extends RestApiException {

    public NonProfileExsistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
