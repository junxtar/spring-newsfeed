package com.sparta.springnewsfeed.domain.post.service;

import static com.sparta.springnewsfeed.domain.heart.constant.HeartConstant.DEFAULT_HEART;
import static com.sparta.springnewsfeed.domain.post.constant.PostConstant.DEFAULT_HEART_COUNT;

import com.sparta.springnewsfeed.domain.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import com.sparta.springnewsfeed.domain.heart.entity.Heart;
import com.sparta.springnewsfeed.domain.post.dto.PostMyResponseDto;
import com.sparta.springnewsfeed.domain.post.dto.PostRequestDto;
import com.sparta.springnewsfeed.domain.post.dto.PostResponseDto;
import com.sparta.springnewsfeed.domain.post.dto.SelectPostResponseDto;
import com.sparta.springnewsfeed.domain.post.dto.UsersPostResponseDto;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.post.exception.PostErrorCode;
import com.sparta.springnewsfeed.domain.post.exception.PostExistsException;
import com.sparta.springnewsfeed.domain.post.repository.PostRepository;
import com.sparta.springnewsfeed.domain.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public SelectPostResponseDto getPost(Long postId, User user) {
        Post post = findById(postId);
        List<CommentResponseDto> commentResponseDtoList = commentList(post);
        Boolean isHearted = getHearted(user, post);

        return SelectPostResponseDto.of(post, isHearted, commentResponseDtoList);
    }

    public List<PostResponseDto> getPostList(User user) {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        return postList.stream()
            .map(post -> PostResponseDto.of(post, getHearted(user, post)))
            .collect(Collectors.toList());
    }

    public List<PostMyResponseDto> getMyPostList(User user) {
        List<Post> postList = postRepository.findAllByUserOrderByCreatedAtDesc(user);
        return postList.stream()
            .map(post -> PostMyResponseDto.of(post, getHearted(user, post)))
            .collect(Collectors.toList());
    }

    public List<UsersPostResponseDto> getUsersPostList(User user) {
        List<Post> postList = postRepository.findAllByUserNotOrderByCreatedAtDesc(user);
        return postList.stream()
            .map(post -> UsersPostResponseDto.of(post, getHearted(user, post)))
            .collect(Collectors.toList());
    }

    @Transactional
    public PostMyResponseDto createPost(PostRequestDto requestDto, User user) {
        Post savePost = Post.builder()
            .title(requestDto.getTitle())
            .content(requestDto.getContent())
            .heartCnt(DEFAULT_HEART_COUNT)
            .user(user)
            .build();

        postRepository.save(savePost);

        return PostMyResponseDto.of(savePost, getHearted(user, savePost));
    }

    @Transactional
    public PostMyResponseDto updatePost(Long postId, PostRequestDto requestDto, User user) {
        Post post = findById(postId);
        findByUsername(post, user.getUsername());
        post.update(requestDto);

        return PostMyResponseDto.of(post, getHearted(user, post));
    }

    @Transactional
    public void deletePost(Long postId, User user) {
        Post post = findById(postId);
        findByUsername(post, user.getUsername());
        postRepository.delete(post);
    }

    private Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(
            () -> new PostExistsException(PostErrorCode.NOT_EXISTS_POST));
    }

    private void findByUsername(Post post, String username) {
        if (!post.getUser().getUsername().equals(username)) {
            throw new PostExistsException(PostErrorCode.NOT_PERMISSION);
        }
    }

    private List<CommentResponseDto> commentList(Post post) {
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        List<Comment> commentList = post.getCommentList();
        commentList.sort((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));
        for (Comment comment : commentList) {
            commentResponseDtoList.add(
                CommentResponseDto.of(comment, comment.getUser().getUsername()));
        }
        return commentResponseDtoList;
    }

    private Boolean getHearted(User user, Post post) {
        return post.getHeartList()
            .stream()
            .filter(heart -> heart.getUser().getId().equals(user.getId()))
            .findFirst()
            .map(Heart::getIsHearted)
            .orElse(DEFAULT_HEART);
    }
}
