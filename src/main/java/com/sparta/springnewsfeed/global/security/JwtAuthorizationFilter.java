package com.sparta.springnewsfeed.global.security;

import static com.sparta.springnewsfeed.global.jwt.JwtUtil.ACCESS_TOKEN_HEADER;
import static com.sparta.springnewsfeed.global.jwt.JwtUtil.REFRESH_TOKEN_HEADER;

import com.sparta.springnewsfeed.global.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j(topic = "JWT 검증 및 인가")
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
        FilterChain filterChain) throws ServletException, IOException {

        String accessToken = jwtUtil.getTokenFromHeader(req, ACCESS_TOKEN_HEADER);

        if (StringUtils.hasText(accessToken)) {
            if (!jwtUtil.validateToken(accessToken)) {
                String refreshToken = jwtUtil.getTokenFromHeader(req, REFRESH_TOKEN_HEADER);
                if (StringUtils.hasText(refreshToken)) {
                    if (jwtUtil.validateToken(refreshToken)) {
                        //TODO:한번더 redis 검증-true
                        accessToken = jwtUtil.createAccessToken(
                            jwtUtil.getIdFromToken(refreshToken)).split(" ")[1].trim();
                        log.warn("ref: "+jwtUtil.getUserInfoFromToken(refreshToken));
                    }
                }
            }
            Claims info = jwtUtil.getUserInfoFromToken(accessToken);
            log.warn(info.getSubject());
            try {
                setAuthentication(info.getSubject());
            } catch (Exception e) {
                log.error(e.getMessage());
                return;
            }
        }

        filterChain.doFilter(req, res);
    }


    // 인증 처리
    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    // 인증 객체 생성
    private Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
    }
}