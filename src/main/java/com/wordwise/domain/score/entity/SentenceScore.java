package com.wordwise.domain.score.entity;

import com.wordwise.common.utils.Timestamped;
import com.wordwise.domain.sentence.entity.Sentence;
import com.wordwise.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "sentence_score")
public class SentenceScore extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private Long id;

    @Column(nullable = false)
    private Long score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sentence_id")
    private Sentence sentence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    private SentenceScore(Long score, Sentence sentence, User user) {
        this.score = score;
        this.sentence = sentence;
        this.user = user;
    }


    public static SentenceScore of(Long score, Sentence sentence, User user) {
        return new SentenceScore(score, sentence, user);
    }

    public void updateScore(Long score) {
        this.score = score;
    }



}
