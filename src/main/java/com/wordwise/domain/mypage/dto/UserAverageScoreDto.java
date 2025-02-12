package com.wordwise.domain.mypage.dto;

import com.wordwise.common.enums.WordType;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UserAverageScoreDto {

    private final WordType type;
    private final Long totalScore;
    private final Long totalCount;
    private final BigDecimal average;

    private UserAverageScoreDto(
            WordType type,
            Long totalScore,
            Long totalCount,
            BigDecimal average
    ) {
        this.type = type;
        this.totalScore = totalScore;
        this.totalCount = totalCount;
        this.average = average;
    }

    public static UserAverageScoreDto of(
            WordType type,
            Long totalScore,
            Long totalCount,
            BigDecimal average
    ) {
        return new UserAverageScoreDto(
                type,
                totalScore,
                totalCount,
                average
        );
    }
}
