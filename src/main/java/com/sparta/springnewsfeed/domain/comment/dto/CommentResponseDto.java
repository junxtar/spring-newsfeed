package com.sparta.springnewsfeed.domain.comment.dto;

import com.sparta.springnewsfeed.domain.comment.entity.Comment;


public class CommentResponseDto {

    private String commentText;

    public CommentResponseDto(Comment saveComment) {
        this.commentText = saveComment.getCommentText();
    }
}
