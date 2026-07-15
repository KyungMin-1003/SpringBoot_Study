package com.example.springboot.springboot.domain.book.exception;

import com.example.springboot.springboot.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BookErrorCode implements BaseErrorCode {

    BOOK_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "BOOK404_1",
            "책을 찾을 수 없습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
