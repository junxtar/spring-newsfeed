package com.sparta.springnewsfeed.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDto {

    private String username;
    private String password;
}
