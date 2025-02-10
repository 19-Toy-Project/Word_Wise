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
    _USER_STATUS_NOT_FOUND(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "존재하지 않는 계정 상태 입니다."),
    _USER_LOGIN_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "존재하지 않는 로그인 타입 입니다."),
    _USER_TIER_NOT_FOUND(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "해당 티어를 찾을 수 없습니다."),
    _USER_NOT_FOUND(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "해당 사용자를 찾을 수 없습니다."),

    // word
    _WORD_TYPE_CODE_NOT_FOUND(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "존재하지 않는 타입 코드 입니다."),
    _NOT_FOUND_WORD(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "존재하지 않는 단어 입니다."),

    //sentence
    _NOT_FOUND_SENTENCE(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "존재하지 않는 문장 입니다."),
    _READ_FILE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.value(),"파일을 읽는 중 서버 오류가 발생했습니다."),

    //wish
    _NOT_FOUND_WISH(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(), "찜하지 않은 문장 입니다"),
    _FOUND_WISH(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(), "찜한 문장입니다"),
    _NOT_EXIST_WISH_SENTENCE(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "찜한 문장이 존재하지 않습니다."),

    //file
    _NOT_FOUND_FILE(HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value(),"파일을 찾을 수 없습니다"),
    _FILE_SIZE_EXCEEDED(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(), "파일 크기를 초과합니다"),
    _UNSUPPORTED_FILE_TYPE(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(), "지원되지 않는 파일 형식입니다"),

    // score
    _DOES_NOT_EXIST_SCORE_DATA(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "점수 데이터가 존재하지 않습니다."),

    // common
    _INVALID_REQUEST(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "잘못된 요청입니다."),
    _PERMISSION_DENIED(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "권한이 없습니다.");

    private HttpStatus httpStatus;
    private Integer statusCode;
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
