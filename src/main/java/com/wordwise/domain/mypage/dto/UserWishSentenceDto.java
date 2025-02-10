package com.wordwise.domain.mypage.dto;

import lombok.Getter;

@Getter
public class UserWishSentenceDto {

    private final Long wordId;
    private final Long sentenceId;
    private final String sentence;
    private final String sentence_kr;

    private UserWishSentenceDto(
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

    public static UserWishSentenceDto of(
            Long wordId,
            Long sentenceId,
            String sentence,
            String sentence_kr
    ) {
        return new UserWishSentenceDto(
                wordId,
                sentenceId,
                sentence,
                sentence_kr
        );
    }
}
