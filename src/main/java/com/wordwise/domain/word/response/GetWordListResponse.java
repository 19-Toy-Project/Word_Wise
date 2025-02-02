package com.wordwise.domain.word.response;

import lombok.Getter;

@Getter
public class GetWordListResponse {
    private Long wordId;
    private String word_en;
    private String type;

    private GetWordListResponse(Long wordId, String word_en, String type) {
        this.wordId = wordId;
        this.word_en = word_en;
        this.type = type;
    }

    public static GetWordListResponse of(Long wordId, String word_en, String type) {
        return new GetWordListResponse(
                wordId,
                word_en,
                type
        );
    }
}
