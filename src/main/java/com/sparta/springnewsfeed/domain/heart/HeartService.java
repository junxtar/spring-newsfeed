package com.sparta.springnewsfeed.domain.heart;

import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.post.exception.PostErrorCode;
import com.sparta.springnewsfeed.domain.post.exception.PostExistsException;
import com.sparta.springnewsfeed.domain.post.repository.PostRepository;
import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void insert(Long userId, Long postId) {
        User user = userRepository.findById(userId).orElseThrow(
            () -> new NullPointerException("유저 정보가 존재하지 않습니다."));
        Post post = findByPost(postId);
        if (heartRepository.findByUserAndPost(user, post).isPresent()) {
            throw new IllegalArgumentException("이미 좋아요 정보가 존재합니다.");
        }
        Heart saveHeart = Heart.builder()
            .user(user)
            .post(post)
            .build();
        post.upHeartCnt();
        heartRepository.save(saveHeart);

    }


    @Transactional
    public void delete(Long userId, Long postId, Long heartId) {
        Heart heart = heartRepository.findById(heartId)
            .orElseThrow(() -> new NullPointerException("좋아요 정보가 존재하지 않습니다."));
        Post post = findByPost(postId);
        if (!heart.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("유저 정보가 일치하지 않습니다.");
        }
        heartRepository.delete(heart);
        post.downHeartCnt();
    }

    ///////////////////////////////////////////////////////////////
    private Post findByPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
            () -> new PostExistsException(PostErrorCode.NOT_EXISTS_POST));
        return post;
    }
    ///////////////////////////////////////////////////////////////
}
