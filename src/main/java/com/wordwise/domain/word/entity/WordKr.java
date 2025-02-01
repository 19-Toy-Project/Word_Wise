package com.wordwise.domain.word.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="word_kr")
@Getter
public class WordKr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String word_kr;

    @ManyToOne
    @JoinColumn(name="word_id",nullable = false)
    private Word word;
}
