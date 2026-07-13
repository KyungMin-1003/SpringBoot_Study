package com.example.springboot.springboot.domain.member.exception;

import com.example.springboot.springboot.global.apiPayload.exception.ProjectException;

public class MemberException extends ProjectException {
    public MemberException(MemberErrorCode errorCode) {
      super(errorCode);
    }
}
