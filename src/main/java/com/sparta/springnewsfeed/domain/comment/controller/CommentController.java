package com.sparta.springnewsfeed.domain.comment.controller;

import com.sparta.springnewsfeed.domain.comment.dto.CommentRequestDto;
import com.sparta.springnewsfeed.domain.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.domain.comment.service.CommentService;
import com.sparta.springnewsfeed.global.common.CommonCode;
import com.sparta.springnewsfeed.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long postId,
        @Valid @RequestBody CommentRequestDto commentRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetail) {
        return ResponseEntity.ok(
            commentService.createComment(postId, commentRequestDto, userDetail.getUser()));
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long postId,
        @Valid @RequestBody CommentRequestDto commentRequestDto,
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetail) {
        return ResponseEntity.ok(
            commentService.updateComment(postId, commentRequestDto, commentId, userDetail.getUser()));
    }


    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId,
                              @PathVariable Long commentId,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(postId, commentId, userDetails.getUser());

        return ResponseEntity.ok(CommonCode.OK.getMessage());
    }

}
