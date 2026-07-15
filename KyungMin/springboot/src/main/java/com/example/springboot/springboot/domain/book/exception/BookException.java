package com.example.springboot.springboot.domain.book.exception;

import com.example.springboot.springboot.global.apiPayload.exception.ProjectException;

public class BookException extends ProjectException {
    public BookException(BookErrorCode errorCode) {
        super(errorCode);
    }
}
