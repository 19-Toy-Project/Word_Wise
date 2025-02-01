package com.wordwise.domain.sentence.service;

import com.wordwise.domain.sentence.response.PapagoApiRequest;
import com.wordwise.domain.sentence.response.PapagoApiResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="papagoTranslationApi", url="https://naveropenapi.apigw.ntruss.com")
public interface PapagoApiClient {

    @PostMapping("/nmt/v1/translation")
    @Headers("Content-Type: application/json")
    PapagoApiResponse getTranslation(
        @RequestHeader("X-NCP-APIGW-API-KEY-ID") String clientId,
        @RequestHeader("X-NCP-APIGW-API-KEY") String clientKey,
        @RequestBody PapagoApiRequest request
    );
}
