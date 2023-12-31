package com.sparta.springnewsfeed.domain.comment.dto;

import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponseDto {

    private String username;
    private String commentText;
    private Long likeCnt;

    @Builder
    private CommentResponseDto(String username, String commentText, Long likeCnt) {
        this.username = username;
        this.commentText = commentText;
        this.likeCnt = likeCnt;
    }

    public static CommentResponseDto of(Comment comment, String username) {

        return CommentResponseDto.builder()
            .commentText(comment.getCommentText())
            .username(username)
            .likeCnt(comment.getLikeCnt())
            .build();

    }
}
