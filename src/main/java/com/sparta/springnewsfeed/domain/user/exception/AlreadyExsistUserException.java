package com.sparta.springnewsfeed.domain.user.exception;

import com.sparta.springnewsfeed.global.exception.*;

public class AlreadyExsistUserException extends RestApiException {

    public AlreadyExsistUserException(ErrorCode errorCode) {
        super(errorCode);
    }

}
