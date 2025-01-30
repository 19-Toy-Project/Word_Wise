package com.wordwise.domain.sentence.service;

import com.wordwise.domain.sentence.response.SaveSentenceResponse;
import com.wordwise.domain.word.entity.Word;
import com.wordwise.domain.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SentenceService {
    private final WordRepository wordRepository;

    public SaveSentenceResponse saveSentence(String word){
        //단어가 존재하는 지 확인


    }
}
