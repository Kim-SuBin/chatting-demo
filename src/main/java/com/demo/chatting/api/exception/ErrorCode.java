package com.demo.chatting.api.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Common
    INTERNAL_SERVER_ERROR(500, "C001", "서버 내부 에러입니다."),
    EXTERNAL_SERVER_ERROR(501, "C002", "외부 API 통신 에러입니다."),

    // Member
    DUPLICATE_NICKNAME(400, "M001", "이미 등록된 닉네임입니다."),
    INVALID_LOGIN_INPUT(401, "M002", "잘못된 로그인 입력 값입니다."),
    ;

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
