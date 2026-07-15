package com.example.springboot.springboot.domain.rental.exception;

import com.example.springboot.springboot.global.apiPayload.exception.ProjectException;

public class RentalException extends ProjectException {

    public RentalException(RentalErrorCode errorCode) {
        super(errorCode);
    }
}
