package com.wordwise.common.apipayload.status;

import com.wordwise.common.apipayload.BaseCode;
import com.wordwise.common.apipayload.dto.ReasonDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorStatus implements BaseCode {

    // user
    _USER_STATUS_NOT_FOUND(HttpStatus.NOT_FOUND, "404", "존재하지 않는 계정 상태 입니다."),
    _USER_LOGIN_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "404", "존재하지 않는 로그인 타입 입니다."),
    _USER_TIER_NOT_FOUND(HttpStatus.NOT_FOUND, "404", "해당 티어를 찾을 수 없습니다."),
    _USER_NOT_FOUND(HttpStatus.NOT_FOUND, "404", "해당 사용자를 찾을 수 없습니다."),

    // word
    _WORD_TYPE_CODE_NOT_FOUND(HttpStatus.NOT_FOUND, "404", "존재하지 않는 타입 코드 입니다."),
    _NOT_FOUND_WORD(HttpStatus.NOT_FOUND, "404", "존재하지 않는 단어 입니다."),

    // common
    _INVALID_REQUEST(HttpStatus.NOT_FOUND, "404", "잘못된 요청입니다."),
    _PERMISSION_DENIED(HttpStatus.BAD_REQUEST, "404", "권한이 없습니다.");

    private HttpStatus httpStatus;
    private String statusCode;
    private String message;


    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .statusCode(statusCode)
                .message(message)
                .httpStatus(httpStatus)
                .success(false)
                .build();
    }
}
