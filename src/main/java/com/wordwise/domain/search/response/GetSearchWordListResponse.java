package com.wordwise.domain.search.response;

import com.wordwise.domain.word.response.WordKrResponse;
import lombok.Getter;
import java.util.List;

@Getter
public class GetSearchWordListResponse {
    private Long wordId;
    private String word_en;
    private List<WordKrResponse> word_krs;
    private String type;

    private GetSearchWordListResponse(Long wordId, String word_en, List<WordKrResponse> word_krs,String type){
        this.wordId=wordId;
        this.word_en=word_en;
        this.word_krs=word_krs;
        this.type=type;
    }

    public static GetSearchWordListResponse of(Long wordId, String word_en, List<WordKrResponse> word_krs,String type){
        return new GetSearchWordListResponse(
                wordId,
                word_en,
                word_krs,
                type
        );
    }
}
