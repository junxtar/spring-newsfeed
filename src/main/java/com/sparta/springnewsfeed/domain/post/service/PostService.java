package com.sparta.springnewsfeed.domain.post.service;

import com.sparta.springnewsfeed.domain.post.dto.PostRequestDto;
import com.sparta.springnewsfeed.domain.post.dto.PostResponseDto;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.post.repository.PostRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post savePost = postRepository.save(new Post(requestDto,user));
        return new PostResponseDto(savePost);
    }

    public List<PostResponseDto> getPostList(User user) {
        List<Post> postList = postRepository.findAllByUserOrderByCreatedAtDesc(user);
        List<PostResponseDto> responseDtoList = new ArrayList<>();
        for (Post post: postList) {
            responseDtoList.add(new PostResponseDto(post));
        }
        return responseDtoList;
    }

    public PostResponseDto getPost(Long postId, User user) {
        Post post = findById(postId);
        findByUsername(post,user.getUsername());
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto, User user) {
        Post post = findById(postId);
        findByUsername(post,user.getUsername());
        post.update(requestDto);
        return new PostResponseDto(post);
    }

    @Transactional
    public void deletePost(Long postId, User user) {
        Post post = findById(postId);
        findByUsername(post,user.getUsername());
        postRepository.delete(post);
    }

    ///////////////////////////////////////////////////////
    private Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(
            () -> new NullPointerException("해당 게시물이 존재하지 않습니다."));
    }

    private void findByUsername(Post post, String username) {
        if (!post.getUser().getUsername().equals(username)) {
            throw new NullPointerException("존재하지 않는 회원 입니다.");
        }
    }
    ///////////////////////////////////////////////////////
}
