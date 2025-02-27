package com.wordwise.domain.score.service;

import com.wordwise.domain.auth.AuthUser;
import com.wordwise.domain.mypage.response.UserStudyDateListResponse;
import com.wordwise.domain.score.repository.SentenceScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SentenceScoreService {

    private final SentenceScoreRepository sentenceScoreRepository;


    public List<LocalDate> getUserStudyDateList(AuthUser authUser, int year, int month) {
        // 해당 년월의 시작일과 마지막일 계산
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDateTime startDate = yearMonth.atDay(1).atStartOfDay();    // 해당 월의 1일 00:00:00
        LocalDateTime endDate = yearMonth.atEndOfMonth().atTime(23,59,59);  // 해당 월의 마지막 날 23:59:59

        List<LocalDate> userStudyDate = sentenceScoreRepository.findStudyDate(authUser.getId(), startDate, endDate);

        // 데이터가 없을 경우 빈 리스트 반환 (null 방지)
        if(userStudyDate.isEmpty()) {
            return List.of(); // 빈 리스트 반환
        }

        return userStudyDate;
    }
}
