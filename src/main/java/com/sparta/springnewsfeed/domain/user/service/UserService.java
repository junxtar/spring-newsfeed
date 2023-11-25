package com.sparta.springnewsfeed.domain.user.service;

import static com.sparta.springnewsfeed.global.jwt.JwtUtil.REFRESH_TOKEN_HEADER;

import com.sparta.springnewsfeed.domain.user.dto.UserModifyPasswordRequestDto;
import com.sparta.springnewsfeed.domain.user.dto.UserResponseDto;
import com.sparta.springnewsfeed.domain.user.dto.UserSignupRequestDto;
import com.sparta.springnewsfeed.domain.user.dto.UserUpdateProfileRequestDto;
import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.domain.user.exception.AlreadyExistUserException;
import com.sparta.springnewsfeed.domain.user.exception.NonUserExsistException;
import com.sparta.springnewsfeed.domain.user.exception.PasswordIsNotMatchException;
import com.sparta.springnewsfeed.domain.user.exception.RejectedUserExecutionException;
import com.sparta.springnewsfeed.domain.user.exception.UserErrorCode;
import com.sparta.springnewsfeed.domain.user.repository.UserRepository;
import com.sparta.springnewsfeed.global.redis.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final RedisUtil redisUtil;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto getProfile(Long userId) {
        User targetUser = existId(userId);
        return UserResponseDto.of(targetUser);
    }

    @Transactional
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

    @Transactional
    public UserResponseDto updateProfile(Long userId, UserUpdateProfileRequestDto requestDto,
        User user) {
        User targetProfile = targetId(userId, user);
        targetProfile.setContent(requestDto.getContent());

        return UserResponseDto.of(targetProfile);
    }

    @Transactional
    public void modifyPassword(Long userId,
        UserModifyPasswordRequestDto requestDto,
        User user) {
        User targetUser = targetId(userId, user);
        if (!passwordEncoder.matches(requestDto.getCurrentPassword(),
            targetUser.getPassword())) {
            throw new PasswordIsNotMatchException(UserErrorCode.PASSWORD_IS_NOT_MATCH);
        }

        targetUser.setPassword(passwordEncoder.encode(requestDto.getNewPassword()));
    }

    @Transactional
    public void logout(HttpServletRequest request) {
        String refreshToken = extractRefreshToken(request);
        redisUtil.delete(refreshToken);
    }

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

    private String extractRefreshToken(HttpServletRequest request) {
        return request.getHeader(REFRESH_TOKEN_HEADER)
            .split(" ")[1]
            .trim();
    }
}
