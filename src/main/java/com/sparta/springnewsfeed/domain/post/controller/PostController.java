package com.sparta.springnewsfeed.domain.post.controller;

import com.sparta.springnewsfeed.domain.post.dto.PostMyResponseDto;
import com.sparta.springnewsfeed.domain.post.dto.PostRequestDto;
import com.sparta.springnewsfeed.domain.post.dto.PostResponseDto;
import com.sparta.springnewsfeed.domain.post.dto.SelectPostResponseDto;
import com.sparta.springnewsfeed.domain.post.dto.UsersPostResponseDto;
import com.sparta.springnewsfeed.domain.post.service.PostService;
import com.sparta.springnewsfeed.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<SelectPostResponseDto> getPost(@PathVariable Long postId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        SelectPostResponseDto responseDto = postService.getPost(postId, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPostList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<PostResponseDto> responseDtoList = postService.getPostList(userDetails.getUser());
        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/mine")
    public ResponseEntity<List<PostMyResponseDto>> getMyPostList(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<PostMyResponseDto> responseDtoList = postService.getMyPostList(userDetails.getUser());
        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UsersPostResponseDto>> getUsersPostList(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<UsersPostResponseDto> responseDtoList = postService.getUsersPostList(
            userDetails.getUser());
        return ResponseEntity.ok(responseDtoList);
    }

    @PostMapping
    public ResponseEntity<PostMyResponseDto> createPost(@RequestBody PostRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostMyResponseDto responseDto = postService.createPost(requestDto, userDetails.getUser());
        return ResponseEntity.status(201).body(responseDto);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostMyResponseDto> updatePost(@PathVariable Long postId,
        @RequestBody PostRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostMyResponseDto responseDto = postService.updatePost(postId, requestDto,
            userDetails.getUser());
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postId, userDetails.getUser());
    }


}
