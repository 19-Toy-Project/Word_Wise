package com.wordwise.domain.word.repository;

import com.wordwise.common.enums.WordType;
import com.wordwise.domain.word.entity.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word,Long> {
    Page<Word> findAll(Pageable pageable);

    Page<Word> findByType(WordType type, PageRequest pageable);
}
