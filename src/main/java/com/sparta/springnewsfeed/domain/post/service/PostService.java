package com.sparta.springnewsfeed.domain.post.service;

import com.sparta.springnewsfeed.domain.post.dto.PostRequestDto;
import com.sparta.springnewsfeed.domain.post.dto.PostResponseDto;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.post.repository.PostRepository;
import com.sparta.springnewsfeed.domain.user.entity.User;
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

    public PostResponseDto getPost(Long postId, User user) {
        Post post = findById(postId);
        findByUsername(post,user.getUsername());
        return PostResponseDto.of(post,user);
    }

    public List<PostResponseDto> getPostList(User user) {
        List<Post> postList = postRepository.findAllByUserOrderByCreatedAtDesc(user);
        List<PostResponseDto> responseDtoList = new ArrayList<>();
        for (Post post: postList) {
            responseDtoList.add(PostResponseDto.of(post,user));
        }
        return responseDtoList;
    }

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post savePost = Post.builder().title(requestDto.getTitle())
                                      .content(requestDto.getContent())
                                      .user(user)
                                      .build();
        postRepository.save(savePost);
        return PostResponseDto.of(savePost,user);
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto, User user) {
        Post post = findById(postId);
        findByUsername(post,user.getUsername());
        post.update(requestDto);
        return PostResponseDto.of(post,user);
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
