package com.sparta.springnewsfeed.domain.post.dto;

import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostMyResponseDto {
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;

    @Builder
    public PostMyResponseDto(String title, String content, String username, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
    }

    public static PostMyResponseDto of(Post post, User user) {
        return PostMyResponseDto.builder().title(post.getTitle())
            .content(post.getContent())
            .createdAt(post.getCreatedAt())
            .username(user.getUsername())
            .build();
    }
}
