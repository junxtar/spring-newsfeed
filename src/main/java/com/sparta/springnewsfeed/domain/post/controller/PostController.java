package com.sparta.springnewsfeed.domain.post.controller;

import com.sparta.springnewsfeed.domain.post.dto.PostRequestDto;
import com.sparta.springnewsfeed.domain.post.dto.PostResponseDto;
import com.sparta.springnewsfeed.domain.post.service.PostService;
import com.sparta.springnewsfeed.global.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        PostResponseDto responseDto = postService.createPost(requestDto,userDetails.getUser());
        return ResponseEntity.status(201).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPostList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<PostResponseDto> responseDtoList = postService.getPostList(userDetails.getUser());
        return ResponseEntity.ok().body(responseDtoList);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto responseDto = postService.getPost(postId,userDetails.getUser());
        return ResponseEntity.ok().body(responseDto);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId,
                                                      @RequestBody PostRequestDto requestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto responseDto = postService.updatePost(postId,requestDto,userDetails.getUser());
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postId,userDetails.getUser());
    }



}
