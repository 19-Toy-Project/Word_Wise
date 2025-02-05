package com.wordwise.domain.sentence.repository;

import com.wordwise.common.enums.WordType;
import com.wordwise.domain.sentence.entity.Score;
import com.wordwise.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score,Long> {
    Score findByUserAndType(User user, WordType type);
}
