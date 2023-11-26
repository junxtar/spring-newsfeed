package com.sparta.springnewsfeed.domain.commentLike.service;

import static com.sparta.springnewsfeed.domain.commentLike.Constant.LikeConstant.DEFAULT_LIKE;

import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import com.sparta.springnewsfeed.domain.comment.exception.CommentErrorCode;
import com.sparta.springnewsfeed.domain.comment.exception.CommentExistsException;
import com.sparta.springnewsfeed.domain.comment.repository.CommentRepository;
import com.sparta.springnewsfeed.domain.commentLike.dto.LikeResponseDto;
import com.sparta.springnewsfeed.domain.commentLike.entity.Likes;
import com.sparta.springnewsfeed.domain.commentLike.repository.LikeRepository;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.post.repository.PostRepository;
import com.sparta.springnewsfeed.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public LikeResponseDto pressLike(User user, Long postId, Long commentId) {
        Post post = checkPost(postId);
        Comment comment = checkComment(commentId);

        Likes likes = likeRepository.findByUserAndPostAndComment(user, post, comment)
            .orElseGet(() -> saveCommentLike(user, post, comment));

        Boolean updated = likes.updateLike();
        comment.updateLikeCnt(updated);

        return LikeResponseDto.of(likes.getIsLiked());
    }

    @Transactional
    public Likes saveCommentLike(User user, Post post, Comment comment) {

        Likes likes = Likes.builder()
            .user(user)
            .post(post)
            .comment(comment)
            .isLiked(DEFAULT_LIKE)
            .build();

        return likeRepository.save(likes);
    }

    private Comment checkComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
            () -> new CommentExistsException(CommentErrorCode.NOT_EXISTS_COMMENT));
    }

    private Post checkPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
            () -> new CommentExistsException(CommentErrorCode.NOT_EXISTS_POST));
    }

}
