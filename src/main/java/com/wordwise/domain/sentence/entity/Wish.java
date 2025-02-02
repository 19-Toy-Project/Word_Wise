package com.wordwise.domain.sentence.entity;

import com.wordwise.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="sentence_id",nullable = false)
    private Sentence sentence;

    private Wish(Long id,User user,Sentence sentence){
        this.id=id;
        this.user=user;
        this.sentence=sentence;
    }
    public static Wish of(Long id,User user,Sentence sentence){
        return new Wish(id,user,sentence);
    }
}
