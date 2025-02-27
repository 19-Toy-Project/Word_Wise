package com.wordwise.domain.sentence.entity;

import com.wordwise.common.enums.WordType;
import com.wordwise.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Getter
@Table(name = "sentence_total_score")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "total_score_id")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private WordType type;

    @Column(nullable = false)
    private Long total_score;

    @Column(nullable = false)
    private Long total_count;

    @Column(nullable = false, scale = 2)
    private BigDecimal average;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Score(WordType type, Long total_score, Long total_count, BigDecimal average, User user) {
        this.type = type;
        this.total_score = total_score;
        this.total_count = total_count;
        this.average = average;
        this.user = user;
    }

    public static Score of(WordType type, Long total_score, Long total_count, BigDecimal average, User user) {
        return new Score(type, total_score, total_count, average, user);
    }

    public void updateScore(Long score) {
        this.total_score = this.total_score+score;
        this.total_count += 1;
        this.average = BigDecimal.valueOf(this.total_score).divide(BigDecimal.valueOf(this.total_count),2, RoundingMode.HALF_UP);
    }
}
