package com.wordwise.domain.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wordwise.common.apipayload.ApiResponse;
import com.wordwise.domain.auth.AuthUser;
import com.wordwise.domain.auth.request.KakaoUserDeleteRequest;
import com.wordwise.domain.auth.request.LoginRequest;
import com.wordwise.domain.auth.service.KakaoService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final KakaoService kakaoService;

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
    @PostMapping("/v1/auth/kakao/login")
    @PreAuthorize("permitAll()")
    public ApiResponse<String> kakaoLogin(
            @RequestBody LoginRequest code,
            HttpServletResponse response
    ) throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드 Service 전달 후 인증 처리 및 JWT 반환
        return ApiResponse.ok(kakaoService.kakaoLogin(code));
    }

    // 카카오 로그아웃
    @GetMapping("/v1/auth/kakao/logout")
    public ApiResponse<String> kakaoLogout(@AuthenticationPrincipal AuthUser authUser) {
        return ApiResponse.ok("로그아웃 완료");
    }


    // 카카오 회원 탈퇴
    @PutMapping("/v1/auth/kakao/delete")
    public ResponseEntity<Void> kakaoUserDelete(@AuthenticationPrincipal AuthUser authUser, @RequestBody KakaoUserDeleteRequest deleteRequest) {
        Long userId = authUser.getId();
        // todo : AuthService kakaoUserDelete(id, deleteRequest);
        return ResponseEntity.noContent().build();
    }


}
