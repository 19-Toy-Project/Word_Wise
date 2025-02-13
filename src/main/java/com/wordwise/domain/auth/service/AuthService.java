package com.wordwise.domain.auth.service;

import com.wordwise.common.apipayload.status.ErrorStatus;
import com.wordwise.common.enums.UserRole;
import com.wordwise.common.exception.ApiException;
import com.wordwise.common.utils.JwtUtil;
import com.wordwise.domain.auth.entity.RefreshToken;
import com.wordwise.domain.auth.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtUtil jwtUtil;

    private static final String BLACKLIST_PREFIX = "BLACKLIST_";

    // Refresh Token 저장
    @Transactional
    public void saveRefreshToken(Long userId, String refreshToken) {

        RefreshToken refreshTokenEntity = RefreshToken.of(userId, refreshToken);

        refreshTokenRepository.save(refreshTokenEntity);
    }

    // 로그아웃
    @Transactional
    public String logout(String authHeader){

        // Access Token
        String accessToken = authHeader.split("Bearer ")[1];
        Claims claims = jwtUtil.extractClaims(accessToken);
        Long userId = Long.valueOf(claims.getSubject());

        // Access Token 남은 시간 추출
        Date expiration = jwtUtil.getExpiration(accessToken);
        long expireTimeMillis = expiration.getTime() - System.currentTimeMillis();

        // Access Token Blacklist에 저장
        redisTemplate.opsForValue().set(BLACKLIST_PREFIX + accessToken, "logout", expireTimeMillis, TimeUnit.MILLISECONDS);

        // Refresh Token 삭제
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId).orElseThrow(()-> new ApiException(ErrorStatus._NOT_EXIST_REFRESH_TOKEN));
        refreshTokenRepository.delete(refreshToken);

        return "로그아웃 완료";
    }

    // Refresh Token으로 Access Token 발급
    public String refreshAccessToken(String refreshToken) {

        // 토큰에서 사용자 정보 추출
        Claims claims = jwtUtil.extractClaims(refreshToken);
        Long userId = Long.valueOf(claims.getSubject());

        RefreshToken storedToken = refreshTokenRepository.findByUserId(userId)
                .orElseThrow(()-> new ApiException(ErrorStatus._INVALID_REFRESH_TOKEN));

        // 저장된 Refresh Token이랑 동일한지 확인
        if(!storedToken.getRefreshToken().equals(refreshToken)) {
            throw new ApiException(ErrorStatus._MISMATCHED_REFRESH_TOKEN);
        }

        // Refresh Token으로 AccessToken 발급
        String refreshAccessToken = jwtUtil.createAccessToken(userId, claims.get("email", String.class), UserRole.valueOf(claims.get("userRole", String.class)));

        return refreshAccessToken;
    }

}
