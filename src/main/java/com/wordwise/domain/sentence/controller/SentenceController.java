package com.wordwise.domain.sentence.controller;

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

    //예문 저장
    @PostMapping("/v1/sentences/save")
    public ResponseEntity<Void> saveSentence(){
        sentenceService.saveSentence();
        return ResponseEntity.noContent().build();
    }
}
