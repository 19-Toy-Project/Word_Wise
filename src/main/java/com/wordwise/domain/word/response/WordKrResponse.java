package com.wordwise.domain.word.response;

import lombok.Getter;

@Getter
public class WordKrResponse {
    private String word_kr;

    private WordKrResponse(String word_kr){
        this.word_kr=word_kr;
    }
    public static WordKrResponse of(String word_kr){
        return new WordKrResponse(word_kr);
    }
}
