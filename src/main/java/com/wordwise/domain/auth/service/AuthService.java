package com.wordwise.domain.auth.service;

import com.wordwise.common.apipayload.status.ErrorStatus;
import com.wordwise.common.enums.UserRole;
import com.wordwise.common.exception.ApiException;
import com.wordwise.common.utils.JwtUtil;
import com.wordwise.domain.auth.entity.RefreshToken;
import com.wordwise.domain.auth.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    // Refresh Token 저장
    @Transactional
    public void saveRefreshToken(Long userId, String refreshToken) {

        RefreshToken refreshTokenEntity = RefreshToken.of(userId, refreshToken);

        refreshTokenRepository.save(refreshTokenEntity);
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
