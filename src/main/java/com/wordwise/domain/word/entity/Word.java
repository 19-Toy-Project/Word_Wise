package com.wordwise.domain.word.entity;

import com.wordwise.common.enums.WordType;
import com.wordwise.common.utils.Timestamped;
import com.wordwise.domain.sentence.entity.Sentence;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
//@Table(name="word" ,indexes = @Index(name="idx_word_en",columnList = "word_en"))
public class Word extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="word_en",length = 255, nullable = false)
    private String wordEn;

    @Enumerated(EnumType.ORDINAL)
    private WordType type;

    @OneToMany(mappedBy = "word", cascade = CascadeType.REMOVE)
    private List<WordKr> word_krs;

    @OneToMany(mappedBy = "word", cascade = CascadeType.REMOVE)
    private List<Sentence> sentences;

    private Word(Long id, String wordEn, WordType type) {
        this.id = id;
        this.wordEn = wordEn;
        this.type = type;
    }

}
