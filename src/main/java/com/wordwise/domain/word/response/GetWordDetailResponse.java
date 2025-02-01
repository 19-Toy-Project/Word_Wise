package com.wordwise.domain.word.response;

import lombok.Getter;
import java.util.List;

@Getter
public class GetWordDetailResponse {
    private String word;
    private List<WordKrResponse> word_kr;
    private List<SentenceResponse> sentences;

    private GetWordDetailResponse (String word,List<WordKrResponse> word_kr,List<SentenceResponse> sentences){
        this.word=word;
        this.word_kr=word_kr;
        this.sentences=sentences;
    }

    public static GetWordDetailResponse of(String word,List<WordKrResponse> word_kr,List<SentenceResponse> sentences){
        return new GetWordDetailResponse(word,word_kr,sentences);
    }
}
