package com.wordwise.domain.sentence.response;

import lombok.Getter;

@Getter
public class GetSentenceDetailResponse {
    private Long sentenceId;
    private String sentence;
    private String sentence_kr;

    private GetSentenceDetailResponse(Long sentenceId, String sentence, String sentence_kr) {
        this.sentenceId = sentenceId;
        this.sentence = sentence;
        this.sentence_kr = sentence_kr;
    }

    public static GetSentenceDetailResponse of(Long sentenceId, String sentence, String sentence_kr) {
        return new GetSentenceDetailResponse(sentenceId, sentence, sentence_kr);
    }
}
