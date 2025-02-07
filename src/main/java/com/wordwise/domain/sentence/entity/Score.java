package com.wordwise.domain.sentence.entity;

import com.wordwise.common.enums.WordType;
import com.wordwise.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "sentence_score")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private WordType type;

    @Column(nullable = false, scale = 2)
    private BigDecimal total_score;

    @Column(nullable = false)
    private Long total_count;

    @Column(nullable = false, scale = 2)
    private BigDecimal average;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Score(WordType type, BigDecimal total_score, Long total_count, BigDecimal average, User user) {
        this.type = type;
        this.total_score = total_score;
        this.total_count = total_count;
        this.average = average;
        this.user = user;
    }

    public static Score of(WordType type, BigDecimal total_score, Long total_count, BigDecimal average, User user) {
        return new Score(type, total_score, total_count, average, user);
    }

    public void updateScore(BigDecimal score) {
        this.total_score = this.total_score.add(score);
        this.total_count += 1;
        this.average = this.total_score.divide(BigDecimal.valueOf(this.total_count), 2, BigDecimal.ROUND_HALF_UP);
    }
}
