package com.example.springboot.springboot.domain.rental.exception;

import com.example.springboot.springboot.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RentalErrorCode implements BaseErrorCode {

    RENTAL_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "RENTAL404_1",
            "대여 정보를 찾을 수 없습니다."
    ),

    ALREADY_RETURNED(
            HttpStatus.CONFLICT,
            "RENTAL409_1",
            "이미 반납된 도서입니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
