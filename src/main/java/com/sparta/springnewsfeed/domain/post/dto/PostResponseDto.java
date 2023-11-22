package com.sparta.springnewsfeed.domain.post.dto;

import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;

    @Builder
    public PostResponseDto(String title, String content, String username, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
    }

    public static PostResponseDto of(Post post, User user) {
        return PostResponseDto.builder().title(post.getTitle())
            .content(post.getContent())
            .createdAt(post.getCreatedAt())
            .username(user.getUsername())
            .build();
    }
}
