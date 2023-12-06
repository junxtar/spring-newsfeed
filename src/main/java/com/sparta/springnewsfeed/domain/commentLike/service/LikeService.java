package com.sparta.springnewsfeed.domain.commentLike.service;

import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import com.sparta.springnewsfeed.domain.comment.service.CommentService;
import com.sparta.springnewsfeed.domain.commentLike.dto.LikeResponseDto;
import com.sparta.springnewsfeed.domain.commentLike.entity.Likes;
import com.sparta.springnewsfeed.domain.commentLike.repository.LikeRepository;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.post.service.PostService;
import com.sparta.springnewsfeed.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final PostService postService;
    private final CommentService commentService;
    private final LikeRepository likeRepository;

    @Transactional
    public LikeResponseDto pressLike(User user, Long postId, Long commentId) {
        Post post = postService.findById(postId);
        Comment comment = commentService.checkComment(commentId);

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
            .build();

        return likeRepository.save(likes);
    }

}
