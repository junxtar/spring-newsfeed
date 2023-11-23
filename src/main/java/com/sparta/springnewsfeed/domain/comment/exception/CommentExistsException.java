package com.sparta.springnewsfeed.domain.comment.exception;

import com.sparta.springnewsfeed.global.exception.ErrorCode;
import com.sparta.springnewsfeed.global.exception.RestApiException;

public class CommentExistsException extends RestApiException {

    public CommentExistsException(ErrorCode errorCode) {
        super(errorCode);
    }
}
