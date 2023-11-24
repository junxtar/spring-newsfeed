package com.sparta.springnewsfeed.domain.user.dto;

import com.sparta.springnewsfeed.domain.user.entity.*;
import lombok.*;

@Getter
@Setter
public class UserModifyPasswordResponse {

    private String password;

    public UserModifyPasswordResponse(User user) {
        this.password = user.getPassword();
    }
}
