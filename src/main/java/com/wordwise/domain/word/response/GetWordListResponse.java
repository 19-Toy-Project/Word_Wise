package com.wordwise.domain.word.response;

import lombok.Getter;

@Getter
public class GetWordListResponse {
    private final Long wordId;
    private final String word_en;

    private GetWordListResponse(Long wordId,String word_en){
        this.wordId=wordId;
        this.word_en=word_en;
    }

    public static GetWordListResponse of(Long wordId,String word_en){
        return new GetWordListResponse(
                wordId,
                word_en
        );
    }
}
