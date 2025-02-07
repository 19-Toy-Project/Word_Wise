package com.wordwise.domain.word.controller;

import com.wordwise.common.apipayload.ApiResponse;
import com.wordwise.common.enums.WordType;
import com.wordwise.domain.auth.AuthUser;
import com.wordwise.domain.word.response.GetWordDetailResponse;
import com.wordwise.domain.word.response.GetWordListResponse;
import com.wordwise.domain.word.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class WordController {
    private final WordService wordService;

    //단어 전체 조회
    @GetMapping("/v1/words")
    public ApiResponse<Page<GetWordListResponse>> getWordList(
            @RequestParam(required = false) WordType type,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.ok(wordService.getWordList(type, page, size));
    }

    //단어 상세 조회
    @GetMapping("/v1/words/{wordId}")
    public ApiResponse<GetWordDetailResponse> getWord(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long wordId
    ) {
        return ApiResponse.ok(wordService.getWord(authUser, wordId));
    }
}
