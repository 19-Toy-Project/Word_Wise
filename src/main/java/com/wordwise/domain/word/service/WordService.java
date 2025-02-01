package com.wordwise.domain.word.service;

import com.wordwise.domain.word.entity.Word;
import com.wordwise.domain.word.repository.WordRepository;
import com.wordwise.domain.word.response.GetWordListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WordService {

    private final WordRepository wordRepository;

//    //단어 뜻 등록 (한국어)
//    public void saveWordKr(){
//        //Word 테이블의 모든 단어 조회
//        List<Word> words=wordRepository.findAll();
//
//        //papago API 호출
//
//    }
    public Page<GetWordListResponse> getWordList(int page, int size){
        PageRequest pageable =PageRequest.of(page-1,size);

        Page<Word> words=wordRepository.findAll(pageable);

        log.info("words",words);
        return words.map(word->GetWordListResponse.of(
                word.getId(),
                word.getWord_en()
        ));

    }
}
