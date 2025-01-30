package com.wordwise.domain.sentence.controller;

import com.wordwise.common.apipayload.ApiResponse;
import com.wordwise.domain.sentence.response.SaveSentenceResponse;
import com.wordwise.domain.sentence.service.SentenceService;
import com.wordwise.domain.sentence.service.WordsApiClient;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SentenceController {

    private final SentenceService sentenceService;

    //단어 예문 등록
    @GetMapping("/v1/sentence/{word}")
    public ApiResponse<SaveSentenceResponse> saveSentence(
            @PathVariable("word") String word
    ){
        return ApiResponse.ok(sentenceService.saveSentence(word));
    }



}
