package com.wordwise.domain.sentence.entity;

import com.wordwise.common.utils.Timestamped;
import com.wordwise.domain.word.entity.Word;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Sentence extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000,nullable = false)
    private String sentence_en;

    @Column(length = 1000)
    private String sentence_kr;

    @ManyToOne
    @JoinColumn(name="word_id",nullable = false)
    private Word word;

    private Sentence(String sentence_en,String sentence_kr, Word word){
        this.sentence_en=sentence_en;
        this.sentence_kr=sentence_kr;
        this.word=word;
    }
}
