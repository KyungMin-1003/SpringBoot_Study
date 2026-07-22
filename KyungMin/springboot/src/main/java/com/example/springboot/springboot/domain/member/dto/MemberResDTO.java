package com.example.springboot.springboot.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberResDTO {

    @Getter
    @AllArgsConstructor
    public static class SignUpResultDto {
        private Long memberId;
        private String name;
        private String email;
    }

    @Getter
    @AllArgsConstructor
    public static class LoginResultDto {
        private String accessToken;
        private Long memberId;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    public static class LogoutResultDto {
        private String message;
    }

    @Getter
    @AllArgsConstructor
    public static class MyInfoDto {
        private Long memberId;
        private String name;
        private String email;
        private String phoneNumber;
        private Integer point;
    }

    @Getter
    @AllArgsConstructor
    public static class UpdateMemberResultDto {
        private Long memberId;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    public static class WithdrawResultDto {
        private String message;
    }
}