package com.sparta.springnewsfeed.domain.post.dto;

import com.sparta.springnewsfeed.domain.post.entity.Post;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostMyResponseDto {
    private String title;
    private String content;
    private String username;
    private Long heartCnt;
    private Boolean isHearted;
    private LocalDateTime createdAt;

    @Builder
    private PostMyResponseDto(String title, String content, String username, Long heartCnt,
        Boolean isHearted, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.username = username;
        this.heartCnt = heartCnt;
        this.isHearted = isHearted;
        this.createdAt = createdAt;
    }

    public static PostMyResponseDto of(Post post, Boolean isHearted) {
        return PostMyResponseDto.builder()
            .title(post.getTitle())
            .content(post.getContent())
            .username(post.getUser().getUsername())
            .heartCnt(post.getHeartCnt())
            .isHearted(isHearted)
            .createdAt(post.getCreatedAt())
            .build();
    }
}
