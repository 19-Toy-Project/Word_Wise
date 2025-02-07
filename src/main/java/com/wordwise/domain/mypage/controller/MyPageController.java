package com.wordwise.domain.mypage.controller;

import com.wordwise.common.apipayload.ApiResponse;
import com.wordwise.domain.auth.AuthUser;
import com.wordwise.domain.mypage.dto.UserAverageScoreDto;
import com.wordwise.domain.mypage.response.UserAverageScoreResponse;
import com.wordwise.domain.mypage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyPageController {

    private final MyPageService myPageService;

    // 사용자 학습 평균 점수 조회
    @GetMapping("/v1/users/score")
    public ApiResponse<UserAverageScoreResponse> getUserAverageScore(
            @AuthenticationPrincipal AuthUser authUser
    ) {
        return ApiResponse.ok(myPageService.getUserAverageScore(authUser));
    }

    // 사용자가 찜한 문장 조회



}
