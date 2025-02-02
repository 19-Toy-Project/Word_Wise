package com.wordwise.domain.sentence.response;

import lombok.Getter;

@Getter
public class SaveSentenceResponse {
    private final Long sentenceId;
    private final String sentence_en;
    private final String sentence_kr;

    private SaveSentenceResponse(Long sentenceId, String sentence_en, String sentence_kr) {
        this.sentenceId = sentenceId;
        this.sentence_en = sentence_en;
        this.sentence_kr = sentence_kr;
    }

    public static SaveSentenceResponse of(Long sentenceId, String sentence_en, String sentence_kr) {
        return new SaveSentenceResponse(
                sentenceId,
                sentence_en,
                sentence_kr
        );
    }
}
