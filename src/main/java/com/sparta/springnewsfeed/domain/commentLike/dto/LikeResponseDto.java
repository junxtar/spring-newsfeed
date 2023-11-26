package com.sparta.springnewsfeed.domain.commentLike.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeResponseDto {

    private Boolean isLiked;

    @Builder
    private LikeResponseDto(Boolean isLiked) {
        this.isLiked = isLiked;
    }

    public static LikeResponseDto of(Boolean isLiked) {
        return LikeResponseDto.builder()
            .isLiked(isLiked)
            .build();
    }
}
