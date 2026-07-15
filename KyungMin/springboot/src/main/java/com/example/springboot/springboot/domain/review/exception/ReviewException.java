package com.example.springboot.springboot.domain.review.exception;

import com.example.springboot.springboot.global.apiPayload.exception.ProjectException;

public class ReviewException extends ProjectException {

    public ReviewException(ReviewErrorCode errorCode) {
        super(errorCode);
    }
}
