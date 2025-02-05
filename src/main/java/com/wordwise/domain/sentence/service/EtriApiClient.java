package com.wordwise.domain.sentence.service;

import com.wordwise.domain.sentence.request.EtriApiRequest;
import com.wordwise.domain.sentence.response.EtriApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="etriPronunciationApi", url="http://aiopen.etri.re.kr:8000")
public interface EtriApiClient {

    @PostMapping("/WiseASR/Pronunciation")
    EtriApiResponse getPronunciationScore(
        @RequestHeader("Authorization") String clientKey,
        @RequestBody EtriApiRequest request
    );

}
