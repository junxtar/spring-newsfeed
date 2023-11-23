package com.sparta.springnewsfeed.domain.comment.service;

import com.sparta.springnewsfeed.domain.comment.dto.CommentRequestDto;
import com.sparta.springnewsfeed.domain.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import com.sparta.springnewsfeed.domain.comment.repository.CommentRepository;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.post.repository.PostRepository;
import com.sparta.springnewsfeed.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto, User user) {
        Post post = checkPost(postId);

        Comment saveComment = Comment.builder()
            .post(post)
            .commentText(commentRequestDto.getCommentText())
            .user(user)
            .build();

        commentRepository.save(saveComment);

        return CommentResponseDto.of(saveComment, user.getUsername());
    }

    @Transactional
    public CommentResponseDto updateComment(Long postId, CommentRequestDto commentRequestDto,
                                            Long commentId, User user) {
        Post post = checkPost(postId);
        Comment exist = checkComment(commentId);
        Comment authority = checkAuthority(exist, user);

        authority.update(commentRequestDto.getCommentText());

        return CommentResponseDto.builder()
            .username(authority.getUser().getUsername())
            .commentText(commentRequestDto.getCommentText())
            .build();
    }

    private Comment checkAuthority(Comment comment, User user) {
        if (!comment.getUser().getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        return comment;
    }

    private Comment checkComment(Long commentId) {
        return commentRepository.findById(commentId)
            .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
    }

    private Post checkPost(Long postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
    }
}
