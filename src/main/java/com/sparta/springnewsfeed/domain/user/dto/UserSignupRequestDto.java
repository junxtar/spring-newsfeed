package com.sparta.springnewsfeed.domain.user.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class UserSignupRequestDto {

    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z]{4,10}+$", message = "유저의 이름은 영문자와 숫자 중 4글자 이상 10글자 이하의 조합입니다.")
    @Column(nullable = false, unique = true)
    private String username;
    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z]{8,15}+$", message = "비밀번호는 영문자와 숫자 중 8글자 이상 15글자 이하의 조합입니다.")
    @Column(nullable = false)
    private String password;

}
