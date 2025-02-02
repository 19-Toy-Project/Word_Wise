package com.wordwise.common.enums;

import com.wordwise.common.apipayload.status.ErrorStatus;
import com.wordwise.common.exception.ApiException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum LoginType {

    KAKAO("카카오 회원");

    private final String type;

    public static LoginType ofLoginType(String type){
        return Arrays.stream(LoginType.values())
                .filter(t -> t.getType().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new ApiException(ErrorStatus._USER_LOGIN_TYPE_NOT_FOUND));
    }
}
