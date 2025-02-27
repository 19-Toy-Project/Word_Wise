package com.wordwise.domain.mypage.response;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserStudyDateListResponse {

    private final LocalDate studyDate;

    private UserStudyDateListResponse(LocalDate studyDate) {
        this.studyDate = studyDate;
    }

    public static UserStudyDateListResponse of(LocalDate studyDate) {
        return new UserStudyDateListResponse(studyDate);
    }
}
