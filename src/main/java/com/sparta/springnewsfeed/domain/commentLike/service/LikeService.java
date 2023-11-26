package com.sparta.springnewsfeed.domain.commentLike.service;

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

import static com.sparta.springnewsfeed.domain.commentLike.Constant.LikeConstant.DEFAULT_LIKE;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public LikeResponseDto pressLike(User user, Long commentId, Long postId) {
        Post post = checkPost(postId);
        Comment comment = checkComment(commentId);
//        checkAuthority(user, comment);      // 이거 하면 좋아요는 코멘트 단 사람만 할 수 있게 되는거 아닌가?

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

//    private static void checkAuthority(User user, Comment comment) {
//        if (!comment.getUser().getUsername().equals(user.getUsername())) {
//            throw new CommentExistsException(CommentErrorCode.UNAUTHORIZED_USER);
//        }
//    }               // 유저 권한 검증인데 왜 필요할까??

    private Comment checkComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
            () -> new CommentExistsException(CommentErrorCode.NOT_EXISTS_COMMENT));
    }

    private Post checkPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
            () -> new CommentExistsException(CommentErrorCode.NOT_EXISTS_POST));
    }

}
