package com.wordwise.domain.sentence.response;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class SaveSentenceScoreResponse {
    private final BigDecimal score;

    private SaveSentenceScoreResponse(BigDecimal score){
        this.score=score;
    }
    public static SaveSentenceScoreResponse of(BigDecimal score){
        return new SaveSentenceScoreResponse(score);
    }
}
