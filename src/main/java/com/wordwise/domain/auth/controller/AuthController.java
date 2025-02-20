package com.wordwise.domain.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wordwise.common.apipayload.ApiResponse;
import com.wordwise.domain.auth.AuthUser;
import com.wordwise.domain.auth.request.KakaoUserDeleteRequest;
import com.wordwise.domain.auth.request.LoginRequest;
import com.wordwise.domain.auth.response.LoginResponse;
import com.wordwise.domain.auth.service.AuthService;
import com.wordwise.domain.auth.service.KakaoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

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
            HttpServletResponse response,
            HttpServletRequest request
    ) throws JsonProcessingException {
        log.info("login api 호출");
        // code: 카카오 서버로부터 받은 인가 코드 Service 전달 후 인증 처리 및 JWT 반환
        // 로그인 수행 (JWT 발급)
        LoginResponse loginResponse = kakaoService.kakaoLogin(code);
        log.info("loginResponse: {}",loginResponse.toString());

        boolean isLocal = request.getServerName().equals("localhost");

        log.info("response header before : {}", response.getHeader(HttpHeaders.SET_COOKIE));

        // RefreshToken을 쿠키에 저장
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", loginResponse.getRefreshToken())
                .httpOnly(true)   // JavaScript에서 접근 불가
                .secure(!isLocal)     // HTTPS 환경에서만 사용
                .sameSite("None") // CORS 환경에서 사용 가능
                .path("/")        // 모든 API에서 쿠키 사용 가능
                .maxAge(Duration.ofDays(7)) // 7일간 유지
                .build();

        // `Set-Cookie` 추가 시 예외 발생 여부 확인
        try {
            response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
            log.info("🚀 Set-Cookie 설정 완료: {}", refreshTokenCookie);
        } catch (Exception e) {
            log.error("🚨 Set-Cookie 설정 중 오류 발생", e);
        }
//
//        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        return ApiResponse.ok(loginResponse);
    }

//    // 로그아웃
//    @PostMapping("/v1/auth/logout")
//    public ApiResponse<String> logout(
//            @RequestHeader("Authorization") String authHeader
//    ) {
//        return ApiResponse.ok(authService.logout(authHeader));
//    }

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

        if(refreshToken == null){
            log.info("************ Refresh Token is null *************");
            return ApiResponse.ok("************ Refresh Token is null *************");
        }
        return ApiResponse.ok(authService.refreshAccessToken(refreshToken));
    }

}
