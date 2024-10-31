package com.nbcamp.todoList.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 회원가입 관련 오류
    MEMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "MEMBER_ALREADY_EXISTS", "이미 존재하는 회원입니다."),
    INVALID_SIGNUP_REQUEST(HttpStatus.BAD_REQUEST, "INVALID_SIGNUP_REQUEST", "유효하지 않은 회원가입 요청입니다."),

    // 로그인 관련 오류
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS", "잘못된 이메일 또는 비밀번호입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_NOT_FOUND", "회원을 찾을 수 없습니다."),

    // 기타 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "내부 서버 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    ErrorCode(HttpStatus httpStatus, String errorCode, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}