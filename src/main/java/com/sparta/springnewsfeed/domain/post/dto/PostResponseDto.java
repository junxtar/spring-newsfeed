package com.sparta.springnewsfeed.domain.post.dto;

import com.sparta.springnewsfeed.domain.post.entity.Post;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
    }
}
