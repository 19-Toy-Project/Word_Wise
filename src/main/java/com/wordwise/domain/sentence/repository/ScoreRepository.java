package com.wordwise.domain.sentence.repository;

import com.wordwise.common.enums.WordType;
import com.wordwise.domain.sentence.entity.TotalScore;
import com.wordwise.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<TotalScore,Long> {
    TotalScore findByUserAndType(User user, WordType type);

    List<TotalScore> findByUserIdOrderByType(Long id);
}
