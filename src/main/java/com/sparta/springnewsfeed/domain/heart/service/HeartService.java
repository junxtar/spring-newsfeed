package com.sparta.springnewsfeed.domain.heart.service;

import static com.sparta.springnewsfeed.domain.heart.constant.HeartConstant.DEFAULT_HEART;

import com.sparta.springnewsfeed.domain.heart.dto.ResponseHeartDto;
import com.sparta.springnewsfeed.domain.heart.entity.Heart;
import com.sparta.springnewsfeed.domain.heart.repository.HeartRepository;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.post.exception.PostErrorCode;
import com.sparta.springnewsfeed.domain.post.exception.PostExistsException;
import com.sparta.springnewsfeed.domain.post.repository.PostRepository;
import com.sparta.springnewsfeed.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HeartService {

    private final HeartRepository heartRepository;
    private final PostRepository postRepository;

    @Transactional
    public ResponseHeartDto pressHeart(User user, Long postId) {
        Post post = findByPost(postId);
        Heart heart = heartRepository.findByUserAndPost(user, post)
            .orElseGet(() -> saveHeart(user, post));

        boolean updated = heart.updateHeart();
        post.updateHeartCnt(updated);

        return ResponseHeartDto.of(heart.getIsHearted());
    }

    @Transactional
    public Heart saveHeart(User user, Post post) {
        Heart heart = Heart.builder()
            .user(user)
            .post(post)
            .isHearted(DEFAULT_HEART)
            .build();

        return heartRepository.save(heart);
    }

    private Post findByPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
            () -> new PostExistsException(PostErrorCode.NOT_EXISTS_POST));
    }
}

