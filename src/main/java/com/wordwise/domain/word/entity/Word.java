package com.wordwise.domain.word.entity;

import com.wordwise.common.enums.WordType;
import com.wordwise.common.utils.Timestamped;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter

public class Word extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255,nullable = false)
    private String word_en;

    @Column(length = 255,nullable = false)
    private String word_kr;

    @Enumerated(EnumType.STRING)
    private WordType type;

    private Word(Long id, String word_en,String word_kr,WordType type){
        this.id=id;
        this.word_en=word_en;
        this.word_kr=word_kr;
        this.type=type;
    }
}
