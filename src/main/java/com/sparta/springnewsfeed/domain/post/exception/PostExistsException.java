package com.sparta.springnewsfeed.domain.post.exception;

import com.sparta.springnewsfeed.global.exception.ErrorCode;
import com.sparta.springnewsfeed.global.exception.RestApiException;

public class PostExistsException extends RestApiException {

    public PostExistsException(ErrorCode errorCode) {
        super(errorCode);
    }
}
