package com.wordwise.common.enums;

import com.wordwise.common.apipayload.status.ErrorStatus;
import com.wordwise.common.exception.ApiException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum UserStatus {

    ACTIVE("활성화 계정"),
    DORMANT("휴면 계정"),
    WITHDRAWAL("탈퇴 계정");

    private final String desc;

    public static UserStatus ofStatus(String status) {
        return Arrays.stream(UserStatus.values())
                .filter(s -> s.getDesc().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new ApiException(ErrorStatus._USER_STATUS_NOT_FOUND));
    }

}
