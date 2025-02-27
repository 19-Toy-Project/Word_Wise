package com.wordwise.domain.score.repository;

import com.wordwise.domain.score.entity.SentenceScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SentenceScoreRepository extends JpaRepository<SentenceScore, Long> {

    @Query("SELECT s FROM SentenceScore s " +
            "WHERE s.user.id = :userId AND s.sentence.id = :sentenceId " +
            "ORDER BY s.modifiedAt DESC " +
            "LIMIT 1")
    SentenceScore findLatestSentence(
            @Param("userId") Long userId,
            @Param("sentenceId") Long sentenceId
    );

    @Query("SELECT DISTINCT DATE(s.createdAt) FROM SentenceScore s " +
            "WHERE s.user.id = :userId AND s.createdAt BETWEEN :startDate AND :endDate " +
            "ORDER BY DATE(s.createdAt) ASC")
    List<LocalDate> findStudyDate(
            @Param("userId") Long id,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
