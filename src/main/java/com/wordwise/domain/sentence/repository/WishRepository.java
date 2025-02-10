package com.wordwise.domain.sentence.repository;

import com.wordwise.domain.mypage.dto.UserWishSentenceDto;
import com.wordwise.domain.sentence.entity.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WishRepository extends JpaRepository<Wish,Long> {
    Wish findBySentenceIdAndUserId(Long id, Long id1);

    Boolean existsBySentenceIdAndUserId(Long id, Long id1);

    @Query("SELECT new com.wordwise.domain.mypage.dto.UserWishSentenceDto(s.word.id, s.id, s.sentence_en, s.sentence_kr)" +
            "FROM Wish w JOIN Sentence s " +
            "ON w.sentence.id = s.id " +
            "WHERE w.user.id = :userId " +
            "ORDER BY w.createdAt DESC"
    )
    Page<UserWishSentenceDto> findAllUserWishSentence(@Param("userId") Long userId, Pageable pageable);
}
