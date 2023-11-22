package com.sparta.springnewsfeed.domain.comment.controller;

import com.sparta.springnewsfeed.domain.comment.dto.CommentRequestDto;
import com.sparta.springnewsfeed.domain.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.domain.comment.service.CommentService;
import com.sparta.springnewsfeed.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CommentResponseDto createComment(@PathVariable Long postId,
                                            @RequestBody CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetail) {
        return commentService.createComment(postId, commentRequestDto, userDetail.getUser());
    }
}
