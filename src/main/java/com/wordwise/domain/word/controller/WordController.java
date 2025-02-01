package com.wordwise.domain.word.controller;

import com.wordwise.common.apipayload.ApiResponse;
import com.wordwise.domain.word.response.GetWordListResponse;
import com.wordwise.domain.word.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class WordController {
    private final WordService wordService;

//    //단어 한국어 뜻 등록
//    @PostMapping("/v1/words/wordkr")
//    public ResponseEntity<Void> saveWordKr(){
//        wordService.saveWordKr();
//        return ResponseEntity.noContent().build();
//    }
    //단어 리스트 조회
    @GetMapping("/v1/words")
    public ApiResponse<Page<GetWordListResponse>> getWordList(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size
    ){
        return ApiResponse.ok(wordService.getWordList(page,size));
    }

}
