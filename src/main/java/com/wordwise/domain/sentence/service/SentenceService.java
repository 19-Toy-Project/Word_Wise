package com.wordwise.domain.sentence.service;

import com.wordwise.common.apipayload.status.ErrorStatus;
import com.wordwise.common.exception.ApiException;
import com.wordwise.common.utils.FileUtil;
import com.wordwise.domain.auth.AuthUser;
import com.wordwise.domain.sentence.entity.Score;
import com.wordwise.domain.sentence.entity.Sentence;
import com.wordwise.domain.sentence.entity.Wish;
import com.wordwise.domain.sentence.repository.ScoreRepository;
import com.wordwise.domain.sentence.repository.SentenceRepository;
import com.wordwise.domain.sentence.repository.WishRepository;
import com.wordwise.domain.sentence.request.EtriApiRequest;
import com.wordwise.domain.sentence.request.PapagoApiRequest;
import com.wordwise.domain.sentence.response.EtriApiResponse;
import com.wordwise.domain.sentence.response.PapagoApiResponse;
import com.wordwise.domain.sentence.response.SaveSentenceScoreResponse;
import com.wordwise.domain.sentence.response.WordsApiResponse;
import com.wordwise.domain.user.entity.User;
import com.wordwise.domain.user.repository.UserRepository;
import com.wordwise.domain.word.entity.Word;
import com.wordwise.domain.word.repository.WordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.math.BigDecimal;
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
    private final UserRepository userRepository;
    private final ScoreRepository scoreRepository;
    private final WishRepository wishRepository;

    @Value("${rapid.client.key}")
    private String rapidClientKey;

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.key}")
    private String naverClientKey;

    @Value("${etri.client.key}")
    private String etriClientKey;

    //영어 단어 문장 등록
    @Transactional
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
    public void saveWish(AuthUser authUser, Long sentenceId){
        //사용자 가져오기
        User user=userRepository.findById(authUser.getId()).orElseThrow(()->
                new ApiException(ErrorStatus._USER_NOT_FOUND));

        //문장 가져오기
        Sentence sentence=sentenceRepository.findById(sentenceId).orElseThrow(()->
                new ApiException(ErrorStatus._NOT_FOUND_SENTENCE));

        Wish newWish=Wish.of(user,sentence);
        wishRepository.save(newWish);
    }

    //영어 문장 찜 해제
    public void cancelWish(AuthUser authUser, Long sentenceId){
        //사용자 가져오기
        User user=userRepository.findById(authUser.getId()).orElseThrow(()->
                new ApiException(ErrorStatus._USER_NOT_FOUND));

        //문장 가져오기
        Sentence sentence=sentenceRepository.findById(sentenceId).orElseThrow(()->
                new ApiException(ErrorStatus._NOT_FOUND_SENTENCE));

        //문장 찜이 존재하는 지 확인
        Wish wish=wishRepository.findBySentenceIdAndUserId(sentence.getId(),user.getId());

        if(wish==null){
           throw new ApiException(ErrorStatus._NOT_FOUND_WISH);
        }else{
            wishRepository.delete(wish);
        }
    }

    //영어 문장 점수 저장
    @Transactional
    public SaveSentenceScoreResponse saveSentenceScore(AuthUser authUser,Long sentenceId, MultipartFile file) {

        try{
            //파일 유효한지 확인

            //문장 DB에서 문장 가져오기
            Sentence sentence=sentenceRepository.findById(sentenceId).orElseThrow(()->
                    new ApiException(ErrorStatus._NOT_FOUND_SENTENCE));

            //녹음 파일 base64로 인코딩
            String base64Data= FileUtil.encodeFileToBase64(file);

            //Etri 발음 API 호출
            EtriApiRequest.Argument argument= EtriApiRequest.Argument.of("english",sentence.getSentence_en(),base64Data);
            EtriApiRequest request=EtriApiRequest.of(argument);
            EtriApiResponse etriApiResponse=etriApiClient.getPronunciationScore(etriClientKey,request);

            //발음 점수 객체 생성 및 저장
            BigDecimal getScore=BigDecimal.valueOf(Double.parseDouble(etriApiResponse.getReturn_object().getScore()));

            //사용자 가져오기
            User user=userRepository.findById(authUser.getId()).orElseThrow(()->
                    new ApiException((ErrorStatus._USER_NOT_FOUND)));

            Score score=scoreRepository.findByUserAndType(user,sentence.getWord().getType());

            //점수 없으면 초기 저장
            if(score==null){
                Score newScore=Score.of(sentence.getWord().getType(),getScore, 1L,getScore,user);
                scoreRepository.save(newScore);
            }else{
                //점수 업데이트
                score.updateScore(getScore);
            }

            return SaveSentenceScoreResponse.of(getScore);

        }catch(IOException e){
            throw new ApiException(ErrorStatus._READ_FILE_ERROR);
        }
    }
}
