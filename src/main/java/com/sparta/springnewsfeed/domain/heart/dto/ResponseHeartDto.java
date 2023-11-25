package com.sparta.springnewsfeed.domain.heart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseHeartDto {

    private Boolean isHearted;

    @Builder
    private ResponseHeartDto(Boolean isHearted) {
        this.isHearted = isHearted;
    }

    public static ResponseHeartDto of(Boolean isHearted) {
        return ResponseHeartDto.builder()
            .isHearted(isHearted)
            .build();
    }
}
