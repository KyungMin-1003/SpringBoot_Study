package com.example.springboot.springboot.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberResDTO {

    @Getter
    @AllArgsConstructor
    public static class SignUpResultDto{
        private Long memberId;
        private String logId;
        private String name;

    }

    @Getter
    @AllArgsConstructor
    public static class LoginResultDto {
        private Long memberId;
        private String accessToken;

    }

    @Getter
    @AllArgsConstructor
    public static class WithdrawResultDto{
        private String message;
    }
}
