package com.sparta.springnewsfeed.global.auth.service;

import com.sparta.springnewsfeed.global.auth.entity.TokenInfo;
import com.sparta.springnewsfeed.global.auth.repository.TokenInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TokenInfoService {

    private final TokenInfoRepository tokenInfoRepository;
    @Transactional
    public void createTokenInfo(String token, Long id) {
        TokenInfo tokenInfo = TokenInfo.builder()
            .refreshToken(token)
            .id(id)
            .build();

//        tokenInfoRepository.save(tokenInfo);
    }

}
