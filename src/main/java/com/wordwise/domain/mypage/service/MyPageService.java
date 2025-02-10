package com.wordwise.domain.mypage.service;

import com.wordwise.common.apipayload.status.ErrorStatus;
import com.wordwise.common.exception.ApiException;
import com.wordwise.domain.auth.AuthUser;
import com.wordwise.domain.mypage.dto.UserAverageScoreDto;
import com.wordwise.domain.mypage.dto.UserWishSentenceDto;
import com.wordwise.domain.mypage.response.UserAverageScoreResponse;
import com.wordwise.domain.mypage.response.UserWishSentenceListResponse;
import com.wordwise.domain.sentence.entity.Score;
import com.wordwise.domain.sentence.repository.ScoreRepository;
import com.wordwise.domain.sentence.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MyPageService {

    private final ScoreRepository scoreRepository;
    private final WishRepository wishRepository;

    // 사용자 학습 평균 점수 조회
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

    // 사용자가 찜한 문장 조회
    public Page<UserWishSentenceListResponse> getUserWishSentenceList(AuthUser authUser, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);

        Page<UserWishSentenceDto> userWishSentences = wishRepository.findAllUserWishSentence(authUser.getId(), pageable);

        // 찜한 문장이 없을때
        if(userWishSentences.getContent().isEmpty()) {
            throw new ApiException(ErrorStatus._NOT_EXIST_WISH_SENTENCE);
        }

        return userWishSentences.map(u -> UserWishSentenceListResponse.of(
                u.getWordId(),
                u.getSentenceId(),
                u.getSentence(),
                u.getSentence_kr()
        ));
    }
}
