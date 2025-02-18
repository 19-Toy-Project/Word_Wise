package com.wordwise.domain.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wordwise.common.apipayload.ApiResponse;
import com.wordwise.domain.auth.AuthUser;
import com.wordwise.domain.auth.request.KakaoUserDeleteRequest;
import com.wordwise.domain.auth.request.LoginRequest;
import com.wordwise.domain.auth.response.LoginResponse;
import com.wordwise.domain.auth.service.AuthService;
import com.wordwise.domain.auth.service.KakaoService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final KakaoService kakaoService;
    private final AuthService authService;

    // 카카오 인가코드 처리 API (로그인) : *** BackEnd TEST 용 ***
    @GetMapping("/v1/auth/kakao/logintest")
    @PreAuthorize("permitAll()")
    public ApiResponse<String> kakaoLoginTest(
            @RequestParam String code,
            HttpServletResponse response
    ) throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드 Service 전달 후 인증 처리 및 JWT 반환
        return ApiResponse.ok(kakaoService.kakaoLoginTest(code));
    }

    // 카카오 인가코드 처리 API (로그인)
    @PostMapping(value = "/v1/auth/login")
    @PreAuthorize("permitAll()")
    public ApiResponse<LoginResponse> login(
            @RequestBody LoginRequest code,
            HttpServletResponse response
    ) throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드 Service 전달 후 인증 처리 및 JWT 반환
        return ApiResponse.ok(kakaoService.kakaoLogin(code));
    }

    // 로그아웃
    @PostMapping("/v1/auth/logout")
    public ApiResponse<String> logout(
            @RequestHeader("Authorization") String authHeader
    ) {
        return ApiResponse.ok(authService.logout(authHeader));
    }

    // 카카오 회원 탈퇴
    @PutMapping("/v1/auth/delete")
    public ResponseEntity<Void> kakaoUserDelete(@AuthenticationPrincipal AuthUser authUser, @RequestBody KakaoUserDeleteRequest deleteRequest) {
        Long userId = authUser.getId();
        // todo : AuthService kakaoUserDelete(id, deleteRequest);
        return ResponseEntity.noContent().build();
    }

    // RefreshToken으로 AccessToken 재발급
    @PostMapping("/v1/auth/token")
    public ApiResponse<String> refreshAccessToken(
            @CookieValue("refreshToken") String refreshToken
    ){
        log.info("Refresh token: {}", refreshToken);
        return ApiResponse.ok(authService.refreshAccessToken(refreshToken));
    }

}
