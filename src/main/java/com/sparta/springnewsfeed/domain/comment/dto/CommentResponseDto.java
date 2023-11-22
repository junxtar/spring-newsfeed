package com.sparta.springnewsfeed.domain.comment.dto;

import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private String username;
    private String commentText;

    @Builder
    private CommentResponseDto(String username, String commentText) {
        this.username = username;
        this.commentText = commentText;
    }

    public static CommentResponseDto of(Comment comment, String username) {

        return CommentResponseDto.builder().commentText(comment.getCommentText())
            .username(username)
            .build();

    }
}
