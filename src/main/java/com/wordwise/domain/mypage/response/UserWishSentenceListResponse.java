package com.wordwise.domain.mypage.response;

import com.wordwise.domain.mypage.dto.UserWishSentenceDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class UserWishSentenceListResponse {

    private final Long wordId;
    private final Long sentenceId;
    private final String sentence;
    private final String sentence_kr;

    private UserWishSentenceListResponse(
            Long wordId,
            Long sentenceId,
            String sentence,
            String sentence_kr
    ) {
        this.wordId = wordId;
        this.sentenceId = sentenceId;
        this.sentence = sentence;
        this.sentence_kr = sentence_kr;
    }

    public static UserWishSentenceListResponse of(
            Long wordId,
            Long sentenceId,
            String sentence,
            String sentence_kr
    ) {
        return new UserWishSentenceListResponse(
                wordId,
                sentenceId,
                sentence,
                sentence_kr
        );
    }
}
