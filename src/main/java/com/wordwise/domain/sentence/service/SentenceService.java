package com.wordwise.domain.sentence.service;

import com.wordwise.common.apipayload.status.ErrorStatus;
import com.wordwise.common.exception.ApiException;
import com.wordwise.common.utils.FileUtil;
import com.wordwise.domain.sentence.entity.Sentence;
import com.wordwise.domain.sentence.repository.SentenceRepository;
import com.wordwise.domain.sentence.request.EtriApiRequest;
import com.wordwise.domain.sentence.request.PapagoApiRequest;
import com.wordwise.domain.sentence.response.EtriApiResponse;
import com.wordwise.domain.sentence.response.PapagoApiResponse;
import com.wordwise.domain.sentence.response.SaveSentenceScoreResponse;
import com.wordwise.domain.sentence.response.WordsApiResponse;
import com.wordwise.domain.word.entity.Word;
import com.wordwise.domain.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SentenceService {
    private final SentenceRepository sentenceRepository;
    private final WordRepository wordRepository;
    private final WordsApiClient wordsApiClient;
    private final PapagoApiClient papagoApiClient;
    private final EtriApiClient etriApiClient;

    @Value("${rapid.client.key}")
    private String rapidClientKey;

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.key}")
    private String naverClientKey;

    @Value("${etri.client.key}")
    private String etriClientKey;

    //영어 단어 문장 등록
    public void saveSentence() {
        //모든 단어 가져오기
        List<Word> words = wordRepository.findAll();

        //각 단어를 WordsAPI 호출
        for (Word word : words) {
            WordsApiResponse response = wordsApiClient.getSentences(rapidClientKey, word.getWord_en());

            //예문 리스트 응답 데이터 (예문 개수 제한 없음)
            List<String> sentences = response.getExamples();

            for (String sentence_en : sentences) {
                //각 예문을 PapagoAPI 호출
                PapagoApiRequest request = PapagoApiRequest.of("en", "ko", sentence_en);
                PapagoApiResponse papagoApiResponse = papagoApiClient.getTranslation(naverClientId, naverClientKey, request);

                //각 예문의 뜻
                String sentence_kr = papagoApiResponse.getMessage().getResult().getTranslatedText();

                //새로운 문장 객체 생성
                Sentence newSentence = Sentence.of(sentence_en, sentence_kr, word);
                sentenceRepository.save(newSentence);
            }

        }
    }

    //영어 문장 찜
    public void saveWish(Long sentenceId){

    }

    //영어 문장 점수 저장
    public SaveSentenceScoreResponse saveSentenceScore(Long sentenceId, MultipartFile file) {

        try{
            //문장 DB에서 문장 가져오기
            Sentence sentence=sentenceRepository.findById(sentenceId).orElseThrow(()->
                    new ApiException(ErrorStatus._NOT_FOUND_SENTENCE));

            //녹음 파일 base64로 인코딩
            String base64Data= FileUtil.encodeFileToBase64(file);

            //Etri 발음 API 호출
            EtriApiRequest.Argument argument= EtriApiRequest.Argument.of("english",sentence.getSentence_kr(),base64Data);
            EtriApiRequest request=EtriApiRequest.of(argument);
            EtriApiResponse etriApiResponse=etriApiClient.getPronunciationScore(etriClientKey,request);

            //발음 점수 객체 생성 및 저장
            return SaveSentenceScoreResponse.of(Double.parseDouble(etriApiResponse.getReturn_object().getScore()));
        }catch(IOException e){
            throw new ApiException(ErrorStatus._READ_FILE_ERROR);
        }
    }
}
