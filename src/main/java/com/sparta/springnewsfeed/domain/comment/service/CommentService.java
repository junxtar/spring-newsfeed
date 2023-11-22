package com.sparta.springnewsfeed.domain.comment.service;

import com.sparta.springnewsfeed.domain.comment.dto.CommentRequestDto;
import com.sparta.springnewsfeed.domain.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import com.sparta.springnewsfeed.domain.comment.repository.CommentRepository;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.post.repository.PostRepository;
import com.sparta.springnewsfeed.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto,
        User user) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("게시물이 없습니다."));

        Comment saveComment = Comment.builder()
            .post(post)
            .commentText(commentRequestDto.getCommentText())
            .user(user)
            .build();

        commentRepository.save(saveComment);

        return CommentResponseDto.of(saveComment, user.getUsername());
    }
}
