package com.sparta.springnewsfeed.domain.user.service;

import com.sparta.springnewsfeed.domain.user.dto.*;
import com.sparta.springnewsfeed.domain.user.entity.*;
import com.sparta.springnewsfeed.domain.user.exception.*;
import com.sparta.springnewsfeed.domain.user.repository.*;
import java.util.*;
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
            throw new AlreadyExsistUserException(UserErrorCode.ALREADY_EXSIST_USER);
        }
        User user = User.builder()
            .username(username)
            .password(password)
            .content("").build();
        userRepository.save(user);
    }

    public UserResponseDto getProfile(Long userId) {
        User targetUser = existId(userId);
        return UserResponseDto.of(targetUser);
    }

    public UserResponseDto updateProfile(Long userId, UserUpdateProfileRequestDto requestDto,
        User user) {
        User targetProfile = writerId(userId, user);
        targetProfile.setContent(requestDto.getContent());
        userRepository.save(targetProfile);
        return UserResponseDto.of(targetProfile);
    }

    private User writerId(Long userId, User user) {
        User targetProfile = existId(userId);
        if (!user.getUsername().equals(targetProfile.getUsername())) {
            throw new RejectedUserExecutionException(UserErrorCode.REJECTED_USER_EXECUTION);
        }
        return targetProfile;
    }

    private User existId(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new NonProfileExsistException(UserErrorCode.NON_PROFILE_EXSIST));
    }
}
