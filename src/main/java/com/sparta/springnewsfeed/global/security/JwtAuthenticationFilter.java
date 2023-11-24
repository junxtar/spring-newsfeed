package com.sparta.springnewsfeed.global.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.springnewsfeed.domain.user.dto.UserLoginRequestDto;
import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.global.auth.service.TokenInfoService;
import com.sparta.springnewsfeed.global.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;
//    private final RedisUtil redisUtil;
    private final TokenInfoService tokenInfoService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, TokenInfoService tokenInfoService) {
        this.jwtUtil = jwtUtil;
//        this.redisUtil = redisUtil;
        this.tokenInfoService = tokenInfoService;
        setFilterProcessesUrl("/api/users/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
        HttpServletResponse response) throws AuthenticationException {
        try {
            log.warn("login");
            UserLoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(),
                UserLoginRequestDto.class);
            return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                    requestDto.getUsername(),
                    requestDto.getPassword(),
                    null
                )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, FilterChain chain, Authentication authResult) {

        User user = ((UserDetailsImpl) authResult.getPrincipal()).getUser();

        String accessToken = jwtUtil.createAccessToken(user.getUsername());
        String refreshToken = jwtUtil.createRefreshToken();

//        redisUtil.set(refreshToken, user.getId(), 60 * 24 * 14);
        tokenInfoService.createTokenInfo(refreshToken, user.getId());

        response.addHeader(JwtUtil.ACCESS_TOKEN_HEADER, accessToken);
        response.addHeader(JwtUtil.REFRESH_TOKEN_HEADER, refreshToken);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(401);
    }

}