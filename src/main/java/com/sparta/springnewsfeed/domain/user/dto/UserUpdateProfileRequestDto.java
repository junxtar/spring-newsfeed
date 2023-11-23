package com.sparta.springnewsfeed.domain.user.dto;

import lombok.*;

@Getter
public class UserUpdateProfileRequestDto {

    private String username;
    private String content;

    public UserUpdateProfileRequestDto(String username, String content) {
        this.username = username;
        this.content = content;
    }
}
