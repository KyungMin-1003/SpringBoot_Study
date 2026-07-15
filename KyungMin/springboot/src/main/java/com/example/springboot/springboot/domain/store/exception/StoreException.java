package com.example.springboot.springboot.domain.store.exception;

import com.example.springboot.springboot.global.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {

    public StoreException(StoreErrorCode errorCode) {
        super(errorCode);
    }
}
