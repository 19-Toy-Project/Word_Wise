package com.wordwise.domain.word.response;

import lombok.Getter;

@Getter
public class GetWordListResponse {
    private final Long wordId;
    private final String word;

    private GetWordListResponse(Long wordId,String word){
        this.wordId=wordId;
        this.word=word;
    }

    public static GetWordListResponse of(Long wordId,String word){
        return new GetWordListResponse(
                wordId,
                word
        );
    }
}
