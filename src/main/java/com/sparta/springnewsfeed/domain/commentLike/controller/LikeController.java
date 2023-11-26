package com.sparta.springnewsfeed.domain.commentLike.controller;

import com.sparta.springnewsfeed.domain.commentLike.dto.LikeResponseDto;
import com.sparta.springnewsfeed.domain.commentLike.service.LikeService;
import com.sparta.springnewsfeed.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post/{postId}/comments/{commentId}/like")
public class LikeController {

    private final LikeService likeService;

    @PatchMapping
    public ResponseEntity<LikeResponseDto> pushLike(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                    @PathVariable Long postId,
                                                    @PathVariable Long commentId) {

        LikeResponseDto likeResponseDto = likeService.pressLike(userDetails.getUser(), postId, commentId);

        return ResponseEntity.ok(likeResponseDto);
    }

}
