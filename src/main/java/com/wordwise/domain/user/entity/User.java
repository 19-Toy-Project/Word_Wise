package com.wordwise.domain.user.entity;

import com.wordwise.common.enums.LoginType;
import com.wordwise.common.enums.UserRole;
import com.wordwise.common.enums.UserStatus;
import com.wordwise.common.enums.UserTier;
import com.wordwise.common.utils.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "kakao_id")
    private Long kakaoId;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_USER;

    @Enumerated(EnumType.STRING)
    private LoginType loginType = LoginType.KAKAO;

    @Enumerated(EnumType.STRING)
    private UserTier tier = UserTier.BRONZE;


    public User(
            Long kakaoId,
            String name,
            String email
    ) {
        this.kakaoId = kakaoId;
        this.name = name;
        this.email = email;
    }

    public static User of(
            Long kakaoId,
            String name,
            String email
    ) {
        return new User(kakaoId, name, email);
    }

    // 다른 소셜 로그인 통합 대비
    public User kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }
}