package com.wordwise.domain.mypage.service;

import com.wordwise.common.apipayload.status.ErrorStatus;
import com.wordwise.common.exception.ApiException;
import com.wordwise.domain.auth.AuthUser;
import com.wordwise.domain.mypage.dto.UserAverageScoreDto;
import com.wordwise.domain.mypage.response.UserAverageScoreResponse;
import com.wordwise.domain.sentence.entity.Score;
import com.wordwise.domain.sentence.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MyPageService {

    private final ScoreRepository scoreRepository;

    public UserAverageScoreResponse getUserAverageScore(AuthUser authUser) {
        // 사용자 Score 객체 가져오기
        List<Score> userScore = scoreRepository.findByUserIdOrderByType(authUser.getId());

        // 사용자 점수가 없을때
        if (userScore.isEmpty()) {
            throw new ApiException(ErrorStatus._DOES_NOT_EXIST_SCORE_DATA);
        }

        List<UserAverageScoreDto> content = userScore.stream().map(s -> UserAverageScoreDto.of(
                s.getType(),
                s.getTotal_score(),
                s.getTotal_count(),
                s.getAverage()
        )).collect(Collectors.toList());

        return UserAverageScoreResponse.from(content);
    }
}
