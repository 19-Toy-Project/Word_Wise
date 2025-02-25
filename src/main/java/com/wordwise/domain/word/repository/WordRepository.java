package com.wordwise.domain.word.repository;

import com.wordwise.common.enums.WordType;
import com.wordwise.domain.word.entity.Word;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word,Long> {
    Page<Word> findAll(Pageable pageable);

    @Query(
        "SELECT w FROM Word w "+
                "WHERE w.wordEn LIKE :searchWord "+
                "ORDER BY "+
                "CASE WHEN w.wordEn =:keyword THEN 1 ELSE 2 END, "+
                "w.wordEn ASC"
    )
    Page<Word> findByWordOrderByASC(@Param("keyword") String keyword, @Param("searchWord") String searchWord, Pageable pageable);
    Page<Word> findByType(WordType type, PageRequest pageable);
}
