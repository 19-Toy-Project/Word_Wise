package com.wordwise.domain.sentence.service;

import com.wordwise.domain.sentence.response.WordsApiResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="wordsApi",url="https://wordsapiv1.p.rapidapi.com")
public interface WordsApiClient {

    @GetMapping("/words/{word}/examples")
    WordsApiResponse getSentences(
            @RequestHeader("x-rapidapi-key") String rapidApiKey,
            @PathVariable String word);

}
