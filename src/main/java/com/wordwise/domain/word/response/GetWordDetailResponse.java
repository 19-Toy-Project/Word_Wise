package com.wordwise.domain.word.response;

import lombok.Getter;

import java.util.List;

@Getter
public class GetWordDetailResponse {
    private List<SentenceResponse> sentences;

    private GetWordDetailResponse(List<SentenceResponse> sentences) {
        this.sentences = sentences;
    }

    public static GetWordDetailResponse of(List<SentenceResponse> sentences) {
        return new GetWordDetailResponse(sentences);
    }
}
