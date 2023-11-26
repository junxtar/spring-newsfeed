package com.sparta.springnewsfeed.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserModifyPasswordRequestDto {

    @NotBlank
    private String currentPassword;

    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z]{8,15}+$", message = "비밀번호는 영문자와 숫자를 포함한 8글자 이상 15글자 이하입니다.")
    private String newPassword;

}
