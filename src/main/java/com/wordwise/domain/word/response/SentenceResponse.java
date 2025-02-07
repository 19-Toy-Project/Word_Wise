package com.wordwise.domain.word.response;

import lombok.Getter;

@Getter
public class SentenceResponse {
    private Long sentenceId;
    private String sentence;
    private String sentence_kr;
    private Boolean wish;

    private SentenceResponse(Long sentenceId, String sentence, String sentence_kr, Boolean wish) {
        this.sentenceId = sentenceId;
        this.sentence = sentence;
        this.sentence_kr = sentence_kr;
        this.wish = wish;
    }

    public static SentenceResponse of(Long sentenceId, String sentence, String sentence_kr, Boolean wish) {
        return new SentenceResponse(sentenceId, sentence, sentence_kr, wish);
    }
}
