package com.example.callbus.Grobal.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //회원가입
    EMAIL_DUPLICATION_409(HttpStatus.CONFLICT, "이미 가입된 회원입니다."),
    NICKNAME_DUPLICATION_409(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다."),

    //로그인
    MEMBER_NOT_FOUND_404(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    PASSWORD_NOT_FOUND_404(HttpStatus.NOT_FOUND, "비밀번호를 찾을 수 없습니다."),
    LOGIN_NOT_FOUND_404(HttpStatus.NOT_FOUND, "아이디 또는 비밀번호를 잘못 입력했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}

