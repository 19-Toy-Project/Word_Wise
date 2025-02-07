package com.wordwise.domain.mypage.response;

import com.wordwise.domain.mypage.dto.UserAverageScoreDto;
import lombok.Getter;

import java.util.List;

@Getter
public class UserAverageScoreResponse {

    private final List<UserAverageScoreDto> content;

    private UserAverageScoreResponse(
            List<UserAverageScoreDto> content
    ) {
        this.content = content;
    }

    public static UserAverageScoreResponse from(
            List<UserAverageScoreDto> content
    ){
        return new UserAverageScoreResponse(content);
    }
}
