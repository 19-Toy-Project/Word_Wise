package com.wordwise.domain.sentence.controller;

import com.wordwise.common.apipayload.ApiResponse;
import com.wordwise.domain.sentence.service.SentenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SentenceController {

    private final SentenceService sentenceService;

    //문장 저장
    @PostMapping("/v1/sentences/save")
    public ApiResponse<String> saveSentence(){
        return ApiResponse.ok(sentenceService.saveSentence());
    }



}
