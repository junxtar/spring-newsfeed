package com.sparta.springnewsfeed.domain.user.controller;

import com.sparta.springnewsfeed.domain.user.dto.*;
import com.sparta.springnewsfeed.domain.user.service.*;
import com.sparta.springnewsfeed.global.common.*;
import com.sparta.springnewsfeed.global.security.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    //TODO:프로필 조회
    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserResponseDto> getProfile(@PathVariable Long userId) {
        UserResponseDto userResponseDto = userService.getProfile(userId);
        return ResponseEntity.ok().body(userResponseDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody UserSignupRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.ok(CommonCode.OK.getMessage());
    }

    @PatchMapping("/profile/{userId}")
    public ResponseEntity<UserResponseDto> updateProfile(@PathVariable Long userId,
        //TODO:프로필 수정
        @RequestBody UserUpdateProfileRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        UserResponseDto userResponseDto = userService.updateProfile(userId, requestDto,
            userDetails.getUser());
        return ResponseEntity.status(201).body(userResponseDto);
    }

    //TODO:로그아웃

    //TODO:비밀번호 수정 *보류*


}
