package com.example.springboot.springboot.domain.member.exception;


import com.example.springboot.springboot.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    //이메일 중복 409 Conflict
    EMAIL_ALREADY_EXISTS(
            HttpStatus.CONFLICT,
            "MEMBER409_1",
            "이미 사용 중인 이메일입니다."
    ),

    //회원 없음: 404 Not Found
    MEMBER_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "회원을 찾을 수 없습니다."
    ),

    //비밀번호 불일치: 401 Unauthorized
    INVALID_PASSWORD(
            HttpStatus.UNAUTHORIZED,
            "MEMBER401_1",
            "비밀번호가 올바르지 않습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
