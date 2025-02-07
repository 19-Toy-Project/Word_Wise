package com.wordwise.domain.sentence.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EtriApiResponse {
    private final Long result;
    private final String return_type;
    private final ReturnObject return_object;

    @Getter
    @AllArgsConstructor
    public static class ReturnObject {
        private final String recognized;
        private final String score;
    }

}
