package com.sparta.springnewsfeed.domain.user.exception;

import com.sparta.springnewsfeed.global.exception.*;

public class RejectedUserExecutionException extends RestApiException {

    public RejectedUserExecutionException(ErrorCode errorCode) {
        super(errorCode);
    }
}
