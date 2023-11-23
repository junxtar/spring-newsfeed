package com.sparta.springnewsfeed.domain.user.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@RequiredArgsConstructor
public class UserUpdateProfileRequestDto {

    @Size(max = 1000, message = "1000자 이내로 작성해 주세요!")
    @Column(nullable = false)
    private String content;

}
