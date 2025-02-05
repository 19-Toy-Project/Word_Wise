package com.wordwise.domain.sentence.response;

import lombok.Getter;

@Getter
public class SaveSentenceScoreResponse {
    private final Double score;

    private SaveSentenceScoreResponse(Double score){
        this.score=score;
    }
    public static SaveSentenceScoreResponse of(Double score){
        return new SaveSentenceScoreResponse(score);
    }
}
