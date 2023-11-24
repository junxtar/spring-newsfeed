package com.sparta.springnewsfeed.domain.comment.exception;

import lombok.Getter;

@Getter
public enum CommonMsg {

    COMMENT_DELETE("댓글이 삭제되었습니다.");

    private final String msg;

    CommonMsg(String msg) {
        this.msg = msg;
    }
}
