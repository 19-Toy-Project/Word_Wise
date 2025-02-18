package com.wordwise.common.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.wordwise.common.enums.UserRole;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.NoSuchElementException;

@Component
public class JwtUtil {

    private static final String BEARER_PREFIX = "Bearer ";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 2 * 60 * 1000L; // 토큰 만료 시간 : 15분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 60 * 60 * 1000L; // 토큰 만료 시간 : 7일

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // Access Token 생성
    public String createAccessToken(Long userId, String email, UserRole userRole) {
        return generateToken(userId, email, userRole, ACCESS_TOKEN_EXPIRE_TIME);
    }

    // Refresh Token 생성
    public String createRefreshToken(Long userId, String email, UserRole userRole) {
        return generateToken(userId, email, userRole, REFRESH_TOKEN_EXPIRE_TIME);
    }

    // Access Token 생성
    public String generateToken(Long userId, String email, UserRole userRole, long expireTime) {
        Date now = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(String.valueOf(userId)) // userId
                        .claim("email", email)  // 사용자 email
                        .claim("userRole", userRole.name()) // 사용자 권한
                        .setExpiration(new Date(now.getTime() + expireTime))   // 토큰 만료 시간
                        .setIssuedAt(now) // 발급일
                        .signWith(key, signatureAlgorithm) // 암호화 알고리즘
                        .compact();
    }

    // JWT 토큰 가공 (substring)
    public String substringToken(String tokenValue) {
        if (StringUtils.hasText(tokenValue) && tokenValue.startsWith(BEARER_PREFIX)) {
            return tokenValue.substring(7);
        }
        throw new NoSuchElementException("Not Found Token");
    }

    // 토큰에서 사용자 정보 추출
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 토큰이 현재 만료되었는지 확인
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // 토큰 만료시간 가져오는 메서드 (토큰 만료 시간을 가져와서 블랙리스트 등록시 만료시간 기준으로 Redis TTL 설정)
    public Date getExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

}
