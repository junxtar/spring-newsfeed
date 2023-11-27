package com.sparta.springnewsfeed.domain.heart.service;

import static com.sparta.springnewsfeed.domain.heart.constant.HeartConstant.DEFAULT_HEART;

import com.sparta.springnewsfeed.domain.heart.dto.ResponseHeartDto;
import com.sparta.springnewsfeed.domain.heart.entity.Heart;
import com.sparta.springnewsfeed.domain.heart.repository.HeartRepository;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.post.service.PostService;
import com.sparta.springnewsfeed.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HeartService {

    private final HeartRepository heartRepository;
    private final PostService postService;

    @Transactional
    public ResponseHeartDto pressHeart(User user, Long postId) {
        Post post = postService.findById(postId);
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
}

