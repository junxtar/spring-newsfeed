package com.sparta.springnewsfeed.domain.user.service;

import com.sparta.springnewsfeed.domain.user.dto.*;
import com.sparta.springnewsfeed.domain.user.entity.*;
import com.sparta.springnewsfeed.domain.user.repository.*;
import java.util.*;
import java.util.concurrent.*;
import lombok.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void signup(UserSignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("이미 등록된 사용자에요!");
        }
        User user = User.builder().username(requestDto.getUsername())
            .password(requestDto.getPassword()).build();
        userRepository.save(user);
    }

    public UserResponseDto getProfile(Long userId) {
        User targetUser = existId(userId); //해당 targetUser의 존재여부 로직.
        //User user는 이미 로그인시 검증 되었고, getProfile은 단지 조회하는 것,
        //그러면 프로필 작성자의 프로필을 조회하는데 userId 이외에 User user가 필요한가?
        //user를 받아와서 수행해야 할 로직은?
        return UserResponseDto.of(targetUser);
    }

//    public UserResponseDto updateProfile(Long userId, UserUpdateProfileRequestDto requestDto,
//        User user) {
//        User targetProfile = writerId(userId, user); //프로필을 수정하려는 사용자가 작성자인지
//        targetProfile.setContent(requestDto.getContent());
//        return new UserResponseDto(targetProfile);
//    }

    private User writerId(Long userId, User user) {
        User targetProfile = existId(userId);
        if (!user.getUsername().equals(targetProfile.getUsername())) {
            throw new RejectedExecutionException("작성자만 수정 할 수 있습니다.");
        }
        return null;
    }

    private User existId(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new NullPointerException("프로필이 존재하지 않습니다."));
    }
}
