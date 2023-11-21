package com.sparta.springnewsfeed.domain.user.service;

import com.sparta.springnewsfeed.domain.user.dto.*;
import com.sparta.springnewsfeed.domain.user.entity.*;
import com.sparta.springnewsfeed.domain.user.repository.*;
import java.util.*;
import org.springframework.stereotype.*;

@Service
public class UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(UserSignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("이미 등록된 사용자에요!");
        }
        User user = new User(username, password);
        userRepository.save(user);
    }

}
