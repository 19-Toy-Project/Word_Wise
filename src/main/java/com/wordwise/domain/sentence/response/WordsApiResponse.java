package com.wordwise.domain.sentence.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class WordsApiResponse {
    private final String word;
    private final List<String> examples;
}
