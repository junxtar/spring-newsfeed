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
            throw new AlreadyExistUserException(UserErrorCode.ALREADY_EXSIST_USER);
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
        User targetProfile = targetId(userId, user);
        targetProfile.setContent(requestDto.getContent());
        userRepository.save(targetProfile);
        return UserResponseDto.of(targetProfile);
    }

    public UserModifyPasswordResponse modifyPassword(Long userId,
        UserModifyPasswordRequestDto requestDto,
        User user) {
        User targetUser = targetId(userId, user);
        if (!passwordEncoder.matches(requestDto.getCurrentPassword(),
            targetUser.getPassword())) {
            throw new PasswordIsNotMatchException(UserErrorCode.PASSWORD_IS_NOT_MATCH);
        }
        targetUser.setPassword(passwordEncoder.encode(requestDto.getNewPassword()));
        userRepository.save(targetUser);
        return new UserModifyPasswordResponse(targetUser);
    }

    ///////////////////////////////////////////////////////
    private User targetId(Long userId, User user) {
        User targetUser = existId(userId);
        if (!user.getUsername().equals(targetUser.getUsername())) {
            throw new RejectedUserExecutionException(UserErrorCode.REJECTED_USER_EXECUTION);
        }
        return targetUser;
    }

    private User existId(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new NonUserExsistException(UserErrorCode.NON_USER_EXSIST));
    }
    ///////////////////////////////////////////////////////
}
