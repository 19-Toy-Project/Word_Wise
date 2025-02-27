package com.wordwise.domain.score.repository;

import com.wordwise.domain.score.entity.SentenceScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SentenceScoreRepository extends JpaRepository<SentenceScore, Long> {

    @Query("SELECT s FROM SentenceScore s " +
            "WHERE s.user.id = :userId AND s.sentence.id = :sentenceId " +
            "ORDER BY s.modifiedAt DESC " +
            "LIMIT 1")
    Optional<SentenceScore> findLatestSentence(@Param("userId") Long userId, @Param("sentenceId") Long sentenceId);
}
