package com.wordwise.domain.sentence.service;

import com.wordwise.domain.sentence.response.WordsApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="wordsApi",url="https://wordsapiv1.p.rapidapi.com/words")
public interface WordsApiClient {

    @GetMapping("/{word}/examples")
    WordsApiResponse getSentences(@PathVariable String word);

}
