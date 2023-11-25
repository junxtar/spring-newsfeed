package com.sparta.springnewsfeed.domain.heart.controller;

import com.sparta.springnewsfeed.domain.heart.dto.ResponseHeartDto;
import com.sparta.springnewsfeed.domain.heart.service.HeartService;
import com.sparta.springnewsfeed.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts/{postId}/hearts")
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @PatchMapping
    public ResponseEntity<ResponseHeartDto> pressHeart(@AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long postId) {
        ResponseHeartDto responseDto = heartService.pressHeart(userDetails.getUser(), postId);

        return ResponseEntity.ok(responseDto);
    }
}
