package com.sparta.springnewsfeed.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequestDto {

    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z]{4,10}+$")
    private String username;
    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z]{8,15}+$")
    private String password;

}
