package com.wordwise.domain.score.repository;

import com.wordwise.domain.score.entity.SentenceScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentenceScoreRepository extends JpaRepository<SentenceScore, Long> {
}
