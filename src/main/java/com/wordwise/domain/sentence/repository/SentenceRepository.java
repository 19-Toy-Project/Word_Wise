package com.wordwise.domain.sentence.repository;

import com.wordwise.domain.sentence.entity.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentenceRepository extends JpaRepository<Sentence,Long> {

}
