package com.wordwise.domain.sentence.response;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class SaveSentenceScoreResponse {
    private final Long score;

    private SaveSentenceScoreResponse(Long score) {
        this.score = score;
    }

    public static SaveSentenceScoreResponse of(Long score) {
        return new SaveSentenceScoreResponse(score);
    }
}
