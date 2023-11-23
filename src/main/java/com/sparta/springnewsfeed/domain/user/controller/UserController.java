package com.sparta.springnewsfeed.domain.user.controller;

import com.sparta.springnewsfeed.domain.user.dto.*;
import com.sparta.springnewsfeed.domain.user.service.*;
import com.sparta.springnewsfeed.global.common.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody UserSignupRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.ok(CommonCode.OK.getMessage());
    }

    //TODO:로그아웃

    //TODO:프로필 조회
    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserResponseDto> getProfile(@PathVariable Long userId) {
        UserResponseDto userResponseDto = userService.getProfile(userId);
        return ResponseEntity.ok().body(userResponseDto);
    }

    //TODO:프로필 수정
//    @PatchMapping("/profile/{userId}")
//    public ResponseEntity<UserResponseDto> updateProfile(@PathVariable Long userId,
//        @RequestBody UserUpdateProfileRequestDto requestDto,
//        @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        UserResponseDto userResponseDto = userService.updateProfile(userId, requestDto,
//            userDetails.getUser());
//    }
    //TODO:비밀번호 수정 *보류*


}
