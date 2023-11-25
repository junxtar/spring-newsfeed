package com.sparta.springnewsfeed.domain.post.dto;

import com.sparta.springnewsfeed.domain.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SelectPostResponseDto {

    private String title;
    private String content;
    private Long heartCnt;
    private String username;
    private Boolean isHearted;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> commentList;

    @Builder
    private SelectPostResponseDto(String title, String content, Long heartCnt, String username,
        Boolean isHearted, LocalDateTime createdAt, List<CommentResponseDto> commentList) {
        this.title = title;
        this.content = content;
        this.heartCnt = heartCnt;
        this.username = username;
        this.isHearted = isHearted;
        this.createdAt = createdAt;
        this.commentList = commentList;
    }

    public static SelectPostResponseDto of(Post post, Boolean isHearted,
        List<CommentResponseDto> responseListDtoList) {
        return SelectPostResponseDto.builder()
            .title(post.getTitle())
            .content(post.getContent())
            .heartCnt(post.getHeartCnt())
            .username(post.getUser().getUsername())
            .isHearted(isHearted)
            .createdAt(post.getCreatedAt())
            .commentList(responseListDtoList)
            .build();
    }
}
