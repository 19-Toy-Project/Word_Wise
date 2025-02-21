package com.wordwise.domain.search.controller;

import com.wordwise.common.apipayload.ApiResponse;
import com.wordwise.domain.search.response.GetSearchWordListResponse;
import com.wordwise.domain.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SearchController {
    private final SearchService searchService;

    //통합 검색
    @GetMapping("/v1/search")
    public ApiResponse<Page<GetSearchWordListResponse>> getSearchWordList(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ApiResponse.ok(searchService.getSearchWordList(keyword,page,size));
    }

}
