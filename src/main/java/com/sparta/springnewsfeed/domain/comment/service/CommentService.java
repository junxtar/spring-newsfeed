package com.sparta.springnewsfeed.domain.comment.service;

import com.sparta.springnewsfeed.domain.comment.dto.CommentRequestDto;
import com.sparta.springnewsfeed.domain.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import com.sparta.springnewsfeed.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto, User user) {

        Comment saveComment = Comment.builder()
                                            .commentText(commentRequestDto.getCommentText())
                                            .user(user)
                                            .build();

        commentRepository.save(saveComment);

        return new CommentResponseDto.of(saveComment, user.getUsername());
    }
}
