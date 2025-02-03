package com.wordwise.domain.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {

    @NotBlank(message="code 전달이 필요합니다.")
    private String code;
}
