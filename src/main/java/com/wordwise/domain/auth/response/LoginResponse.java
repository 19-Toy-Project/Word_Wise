package com.wordwise.domain.auth.response;

import lombok.Getter;

@Getter
public class LoginResponse {

    private final String AccessToken;
    private final String RefreshToken;

    private LoginResponse(
            String accessToken,
            String refreshToken
    ) {
        AccessToken = accessToken;
        RefreshToken = refreshToken;
    }

    public static LoginResponse of(
            String accessToken,
            String refreshToken
    ) {
        return new LoginResponse(accessToken, refreshToken);
    }
}
