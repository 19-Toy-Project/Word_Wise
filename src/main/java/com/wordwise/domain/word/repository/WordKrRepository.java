package com.wordwise.domain.word.repository;

import com.wordwise.domain.word.entity.WordKr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordKrRepository extends JpaRepository<WordKr,Long> {
}
