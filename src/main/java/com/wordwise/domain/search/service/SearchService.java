package com.wordwise.domain.search.service;

import com.wordwise.domain.search.response.GetSearchWordListResponse;
import com.wordwise.domain.word.entity.Word;
import com.wordwise.domain.word.repository.WordRepository;
import com.wordwise.domain.word.response.WordKrResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final WordRepository wordRepository;

    public Page<GetSearchWordListResponse> getSearchWordList(String keyword,int page,int size){
        Pageable pageable=PageRequest.of(page-1,size);

        long startTime = System.nanoTime(); // 검색 시작 시간 기록
        Page<Word> words=wordRepository.findByWordEnOrderByASC(keyword,pageable);
        long endTime = System.nanoTime(); // 검색 종료 시간 기록
        long duration = (endTime - startTime) / 1_000_000; // 밀리초 변환
        System.out.println("검색 소요 시간: " + duration + "ms");
        return words.map(word -> GetSearchWordListResponse.of(
                word.getId(),
                word.getWordEn(),
                word.getWord_krs().stream()
                                .map(wordKr -> WordKrResponse.of(wordKr.getWord_kr()))
                                        .collect(Collectors.toList()),
                word.getType().getType()
        ));
    }
}
