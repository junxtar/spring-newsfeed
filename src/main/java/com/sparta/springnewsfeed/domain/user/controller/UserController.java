package com.sparta.springnewsfeed.domain.user.controller;

import com.sparta.springnewsfeed.domain.user.dto.*;
import com.sparta.springnewsfeed.domain.user.service.*;
import com.sparta.springnewsfeed.global.common.*;
import com.sparta.springnewsfeed.global.security.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

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
        @RequestBody UserUpdateProfileRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        UserResponseDto userResponseDto = userService.updateProfile(userId, requestDto,
            userDetails.getUser());
        return ResponseEntity.status(201).body(userResponseDto);
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<UserModifyPasswordResponse> modifyPassword(@PathVariable Long userId,
        @Valid @RequestBody UserModifyPasswordRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        UserModifyPasswordResponse userResponseDto = userService.modifyPassword(userId, requestDto,
            userDetails.getUser());
        return ResponseEntity.ok().body(userResponseDto);
    }

    //TODO:로그아웃

}
