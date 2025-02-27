package com.wordwise.domain.mypage.controller;

import com.wordwise.common.apipayload.ApiResponse;
import com.wordwise.domain.auth.AuthUser;
import com.wordwise.domain.mypage.response.UserAverageScoreResponse;
import com.wordwise.domain.mypage.response.UserWishSentenceListResponse;
import com.wordwise.domain.mypage.service.MyPageService;
import com.wordwise.domain.score.service.SentenceScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyPageController {

    private final MyPageService myPageService;
    private final SentenceScoreService sentenceScoreService;

    // 사용자 학습 평균 점수 조회
    @GetMapping("/v1/users/score")
    public ApiResponse<UserAverageScoreResponse> getUserAverageScore(
            @AuthenticationPrincipal AuthUser authUser
    ) {
        return ApiResponse.ok(myPageService.getUserAverageScore(authUser));
    }

    // 사용자가 찜한 문장 조회
    @GetMapping("/v1/users/wish/sentence")
    public ApiResponse<Page<UserWishSentenceListResponse>> getUserWishSentenceList(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ApiResponse.ok(myPageService.getUserWishSentenceList(authUser, page, size));
    }

    // 사용자가 학습한 날짜 조회
    @GetMapping("/v2/users/calendar")
    public ApiResponse<List<LocalDate>> getUserStudyDateList(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam int year,
            @RequestParam int month
    ){
        return ApiResponse.ok(sentenceScoreService.getUserStudyDateList(authUser, year, month));
    }



}
