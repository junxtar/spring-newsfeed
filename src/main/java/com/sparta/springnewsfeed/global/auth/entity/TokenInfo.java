package com.sparta.springnewsfeed.global.auth.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "token_info", timeToLive = 60 * 60 * 24 * 14)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenInfo {

    @Id
    private String refreshToken;
    private Long id;

    @Builder
    public TokenInfo(String refreshToken, Long id) {
        this.refreshToken = refreshToken;
        this.id = id;
    }
}
