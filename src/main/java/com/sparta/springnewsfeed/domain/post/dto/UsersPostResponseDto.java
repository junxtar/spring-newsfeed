package com.sparta.springnewsfeed.domain.post.dto;

import com.sparta.springnewsfeed.domain.post.entity.Post;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UsersPostResponseDto {
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;

    @Builder
    public UsersPostResponseDto(String title, String content, String username, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
    }

    public static UsersPostResponseDto of (Post post) {
        return UsersPostResponseDto.builder()
            .title(post.getTitle())
            .content(post.getContent())
            .username(post.getUser().getUsername())
            .createdAt(post.getCreatedAt())
            .build();
    }

}
