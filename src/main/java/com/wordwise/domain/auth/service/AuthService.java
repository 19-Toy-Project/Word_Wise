package com.wordwise.domain.auth.service;

import com.wordwise.domain.auth.entity.RefreshToken;
import com.wordwise.domain.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void saveRefreshToken(Long userId, String refreshToken) {

        RefreshToken refreshTokenEntity = RefreshToken.of(userId, refreshToken);

        refreshTokenRepository.save(refreshTokenEntity);
    }


}
