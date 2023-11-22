package com.sparta.springnewsfeed.domain.comment.dto;

import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import lombok.Builder;
import org.apache.catalina.User;


public class CommentResponseDto {

    private Long id;
    private String commentText;

    @Builder
    public CommentResponseDto(Long id ,String commentText) {
        this.id = id;
        this.commentText = commentText;
    }

    public static CommentResponseDto of (Comment comment, User user) {

        return CommentResponseDto.builder().commentText(comment.getCommentText())
                                            .id(user.getUserId)
                                            .build();

    }
}
