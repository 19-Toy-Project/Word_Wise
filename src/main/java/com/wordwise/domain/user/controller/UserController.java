package com.wordwise.domain.user.controller;

import com.wordwise.common.apipayload.ApiResponse;
import com.wordwise.domain.auth.AuthUser;
import com.wordwise.domain.user.response.UserProfileResponse;
import com.wordwise.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // 개인 프로필 조회
    @GetMapping("/v1/users/profiles")
    public ApiResponse<UserProfileResponse> getUserProfile(@AuthenticationPrincipal AuthUser authUser) {
        return ApiResponse.ok(userService.getUserProfile(authUser));
    }


}
