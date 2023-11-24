package com.sparta.springnewsfeed.domain.user.exception;

import com.sparta.springnewsfeed.global.exception.*;

public class AlreadyExistUserException extends RestApiException {

    public AlreadyExistUserException(ErrorCode errorCode) {
        super(errorCode);
    }

}
