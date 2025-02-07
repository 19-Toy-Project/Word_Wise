package com.wordwise.domain.sentence.controller;

import com.wordwise.common.apipayload.ApiResponse;
import com.wordwise.domain.auth.AuthUser;
import com.wordwise.domain.sentence.response.SaveSentenceScoreResponse;
import com.wordwise.domain.sentence.service.SentenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SentenceController {
    private final SentenceService sentenceService;

    //문장 저장
    @PostMapping("/v1/sentences/save")
    public ApiResponse<Void> saveSentence() {
        sentenceService.saveSentence();
        return ApiResponse.success();
    }

    //문장 찜 & 해제
    @PostMapping("/v1/sentences/wish/{sentenceId}")
    public ApiResponse<Void> saveWish(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long sentenceId,
            @RequestParam String state
    ) {
        sentenceService.saveWish(authUser, sentenceId, state);
        return ApiResponse.success();
    }

    //문장 녹음 점수 저장
    @PostMapping("/v1/sentences/record/{sentenceId}")
    public ApiResponse<SaveSentenceScoreResponse> saveSentenceScore(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long sentenceId,
            @RequestParam(value = "file") MultipartFile multipartFile
    ) {
        return ApiResponse.ok(sentenceService.saveSentenceScore(authUser, sentenceId, multipartFile));
    }
}
