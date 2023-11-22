package com.sparta.springnewsfeed.domain.comment.dto;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @Column(nullable = false, length = 500)
    private String commentText;
}
