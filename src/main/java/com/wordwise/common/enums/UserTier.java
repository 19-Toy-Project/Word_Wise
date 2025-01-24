package com.wordwise.common.enums;

import com.wordwise.common.apipayload.status.ErrorStatus;
import com.wordwise.common.exception.ApiException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum UserTier {

    IRON("아이언"),
    BRONZE("브론즈"),
    SILVER("실버"),
    GOLD("골드"),
    DIAMOND("다이아");

    private final String tier;

    public static UserTier ofTier(String tier) {
        return Arrays.stream(UserTier.values())
                .filter(s -> s.getTier().equalsIgnoreCase(tier))
                .findFirst()
                .orElseThrow(() -> new ApiException(ErrorStatus._USER_TIER_NOT_FOUND));
    }

}
