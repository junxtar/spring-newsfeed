package com.sparta.springnewsfeed.domain.user.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class UserModifyPasswordRequestDto {

    @NotBlank
    private String currentPassword; //{"currentPassword:1234"}
    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z]{8,15}+$", message = "비밀번호는 영문자와 숫자를 포함한 8글자 이상 15글자 이하입니다.")
    @Column(nullable = false)
    private String newPassword;

}
