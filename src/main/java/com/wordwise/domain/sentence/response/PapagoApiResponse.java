package com.wordwise.domain.sentence.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PapagoApiResponse {
    private Message message;

    @Getter
    @AllArgsConstructor
    public static class Message {
        private Result result;
    }

    @Getter
    @AllArgsConstructor
    public static class Result {
        private String srcLangType;
        private String tarLangType;
        private String translatedText;
    }
}
