package com.wordwise.domain.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class KakaoUserDeleteRequest {

    @NotBlank
    @Email(message = "카카오 이메일을 입력해 주세요.")
    private String email;
}
