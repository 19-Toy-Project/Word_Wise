package com.wordwise.domain.mypage.response;

import lombok.Getter;

@Getter
public class UserLearnedSentenceCountResponse {

    private final Long totalSentenceCount;
    private final Long studySentenceCount;

    private UserLearnedSentenceCountResponse(Long totalSentenceCount, Long studySentenceCount) {
        this.totalSentenceCount = totalSentenceCount;
        this.studySentenceCount = studySentenceCount;
    }

    public static UserLearnedSentenceCountResponse of(Long totalSentenceCount, Long studySentenceCount) {
       return new UserLearnedSentenceCountResponse(
               totalSentenceCount,
               studySentenceCount
       );
    }

}
