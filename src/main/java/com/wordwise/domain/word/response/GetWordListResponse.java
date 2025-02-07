package com.wordwise.domain.word.response;

import lombok.Getter;

import java.util.List;

@Getter
public class GetWordListResponse {
    private Long wordId;
    private String word_en;
    private List<WordKrResponse> word_krs;
    private String type;

    private GetWordListResponse(Long wordId, String word_en, List<WordKrResponse> word_krs, String type) {
        this.wordId = wordId;
        this.word_en = word_en;
        this.word_krs = word_krs;
        this.type = type;
    }

    public static GetWordListResponse of(Long wordId, String word_en, List<WordKrResponse> word_krs, String type) {
        return new GetWordListResponse(
                wordId,
                word_en,
                word_krs,
                type
        );
    }
}
