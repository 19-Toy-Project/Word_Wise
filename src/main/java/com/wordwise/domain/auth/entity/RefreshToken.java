package com.wordwise.domain.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="refresh_token")
public class RefreshToken {

    @Id
    @Column(name="user_id")
    private Long userId;    // 사용자 ID (primary key : 중복 불가)

    @Column(name="refresh_token",nullable = false, unique = true)
    private String refreshToken;

    public RefreshToken(
            Long userId,
            String refreshToken
    ) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    private static RefreshToken of(
            Long userId,
            String refreshToken
    ) {
        return new RefreshToken(userId, refreshToken);
    }
}
