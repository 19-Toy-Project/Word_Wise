package com.wordwise.domain.sentence.repository;

import com.wordwise.domain.sentence.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish,Long> {
}
