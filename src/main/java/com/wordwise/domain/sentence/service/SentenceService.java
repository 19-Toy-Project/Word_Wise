package com.wordwise.domain.sentence.service;

import com.wordwise.domain.sentence.response.PapagoApiRequest;
import com.wordwise.domain.sentence.response.PapagoApiResponse;
import com.wordwise.domain.sentence.response.WordsApiResponse;
import com.wordwise.domain.word.entity.Word;
import com.wordwise.domain.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SentenceService {
    private final WordRepository wordRepository;
    private final WordsApiClient wordsApiClient;
    private final PapagoApiClient papagoApiClient;

    @Value("${rapid.client.key}")
    private String rapidApiKey;

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.key}")
    private String clientKey;


    //예문 등록
    public void saveSentence(){
        //모든 단어 가져오기
        List<Word> words=wordRepository.findAll();

        //각 단어를 WordsAPI 호출
        for(Word word: words){
            WordsApiResponse response=wordsApiClient.getSentences(rapidApiKey,word.getWord_en());

            //예문 리스트 응답 데이터 (예문 개수 제한 없음)
            List<String> sentences=response.getExamples();

            log.info("word={}",response.getWord());
            for(String sentence:sentences){
                log.info("sentence={}",sentence);
                //각 예문을 PapagoAPI 호출
                PapagoApiRequest request=PapagoApiRequest.of("en","ko",sentence);
                PapagoApiResponse papagoApiResponse=papagoApiClient.getTranslation(clientId,clientKey,request);
                //각 예문의 뜻
                String meaning=papagoApiResponse.getMessage().getResult().getTranslatedText();


                log.info("meaning={}",meaning);

            }

        }

    }
}
