package com.wordwise.domain.word.service;

import com.wordwise.common.apipayload.status.ErrorStatus;
import com.wordwise.common.enums.WordType;
import com.wordwise.common.exception.ApiException;
import com.wordwise.domain.sentence.entity.Sentence;
import com.wordwise.domain.word.entity.Word;
import com.wordwise.domain.word.repository.WordRepository;
import com.wordwise.domain.word.response.GetWordDetailResponse;
import com.wordwise.domain.word.response.GetWordListResponse;
import com.wordwise.domain.word.response.SentenceResponse;
import com.wordwise.domain.word.response.WordKrResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public Page<GetWordListResponse> getWordList(WordType type,int page, int size){
        PageRequest pageable =PageRequest.of(page-1,size);

        Page<Word> words;

        if(type==null){
            words=wordRepository.findAll(pageable);
        }else{
            words=wordRepository.findByTypeContaining(type,pageable);
        }

        return words.map(word->GetWordListResponse.of(
                word.getId(),
                word.getWord_en(),
                word.getType().getType()
        ));

    }

    //단어 상세 조회
    public GetWordDetailResponse getWord(Long wordId){
        //단어 있는 지 확인
        Word word=wordRepository.findById(wordId).orElseThrow(()->
                new ApiException(ErrorStatus._NOT_FOUND_WORD));

        //단어 리스트 변환
        List<WordKrResponse> wordKrResponses=word.getWord_krs().stream()
                .map(wordKr -> WordKrResponse.of(wordKr.getWord_kr()))
                .collect(Collectors.toList());

        List<SentenceResponse> sentenceResponses=word.getSentences().stream()
                .map(sentence -> SentenceResponse.of(
                        sentence.getId(),
                        sentence.getSentence_en(),
                        sentence.getSentence_kr()
                )).collect(Collectors.toList());

        return GetWordDetailResponse.of(word.getWord_en(),wordKrResponses,sentenceResponses);

    }
}
