package com.wordwise.common.enums;

import com.wordwise.common.apipayload.status.ErrorStatus;
import com.wordwise.common.exception.ApiException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum WordType {

    BEGINNER("초급", 0),
    INTERMEDIATE("중급", 1),
    ADVANCED("고급", 2),
    CONVERSATION("회화", 3);

    private final String type;
    private final Integer code;

    public static Integer getCode() {
        return WordType.getCode();
    }

    public static WordType fromCode(Integer code) {
        return Arrays.stream(WordType.values())
                .filter(c->c.getCode().equals(code))
                .findAny()
                .orElseThrow(()->new ApiException(ErrorStatus._WORD_TYPE_CODE_NOT_FOUND));
    }

}
