package com.sparta.springnewsfeed.domain.user.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class UserSignupRequestDto {

    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z]{4,10}+$")
    private String username;
    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z]+$")
    private String password;
//    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
//    private String email;

}
