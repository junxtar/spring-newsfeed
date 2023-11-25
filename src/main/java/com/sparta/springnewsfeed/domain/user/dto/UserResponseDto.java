package com.sparta.springnewsfeed.domain.user.dto;

import com.sparta.springnewsfeed.domain.user.entity.*;
import lombok.*;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private String username;
    private String content;

    @Builder
    private UserResponseDto(String username, String content) {
        this.username = username;
        this.content = content;
    }

    public static UserResponseDto of(User user) {
        return UserResponseDto.builder()
            .username(user.getUsername())
            .content(user.getContent())
            .build();
    }

}
