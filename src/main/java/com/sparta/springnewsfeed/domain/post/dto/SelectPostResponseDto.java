package com.sparta.springnewsfeed.domain.post.dto;

import com.sparta.springnewsfeed.domain.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SelectPostResponseDto {
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> commentList;

    @Builder
    public SelectPostResponseDto(String title, String content, String username, LocalDateTime createdAt, List<CommentResponseDto> commentList) {
        this.title = title;
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
        this.commentList = commentList;
    }

    public static SelectPostResponseDto of(Post post, User user, List<CommentResponseDto> responseListDtoList) {
        return SelectPostResponseDto.builder()
            .title(post.getTitle())
            .content(post.getContent())
            .createdAt(post.getCreatedAt())
            .username(user.getUsername())
            .commentList(responseListDtoList)
            .build();
    }

}
