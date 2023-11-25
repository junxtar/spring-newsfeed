package com.sparta.springnewsfeed.domain.user.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserUpdateProfileRequestDto {

    @Size(max = 1000, message = "1000자 이내로 작성해 주세요!")
    private String content;

}
