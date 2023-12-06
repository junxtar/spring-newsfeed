package com.sparta.springnewsfeed.domain.comment.service;

import com.sparta.springnewsfeed.domain.comment.dto.CommentRequestDto;
import com.sparta.springnewsfeed.domain.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import com.sparta.springnewsfeed.domain.comment.exception.CommentErrorCode;
import com.sparta.springnewsfeed.domain.comment.exception.CommentExistsException;
import com.sparta.springnewsfeed.domain.comment.repository.CommentRepository;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.post.service.PostService;
import com.sparta.springnewsfeed.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sparta.springnewsfeed.domain.comment.constant.CommentConstant.DEFAULT_LIKE_CNT;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;

    @Transactional
    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto, User user) {
        Post post = postService.findById(postId);

        Comment saveComment = Comment.builder()
            .post(post)
            .commentText(commentRequestDto.getCommentText())
            .user(user)
            .likeCnt(DEFAULT_LIKE_CNT)
            .build();

        commentRepository.save(saveComment);

        return CommentResponseDto.of(saveComment, user.getUsername());
    }

    @Transactional
    public CommentResponseDto updateComment(Long postId, CommentRequestDto commentRequestDto,
                                            Long commentId, User user) {
        postService.findById(postId);
        Comment comment = checkComment(commentId);
        checkAuthority(comment, user);

        comment.update(commentRequestDto.getCommentText());

        return CommentResponseDto.builder()
            .username(comment.getUser().getUsername())
            .commentText(commentRequestDto.getCommentText())
            .likeCnt(comment.getLikeCnt())
            .build();
    }

    @Transactional
    public void deleteComment(Long postId, Long commentId, User user) {
        postService.findById(postId);
        Comment comment = checkComment(commentId);
        checkAuthority(comment, user);

        commentRepository.delete(comment);
    }

    private void checkAuthority(Comment comment, User user) {
        if (!comment.getUser().getUsername().equals(user.getUsername())) {
            throw new CommentExistsException(CommentErrorCode.UNAUTHORIZED_USER);
        }
    }

    public Comment checkComment(Long commentId) {
        return commentRepository.findById(commentId)
            .orElseThrow(() -> new CommentExistsException(CommentErrorCode.NOT_EXISTS_COMMENT));
    }
}