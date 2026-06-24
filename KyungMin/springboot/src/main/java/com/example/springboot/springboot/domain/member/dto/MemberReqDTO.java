package com.example.springboot.springboot.domain.member.dto;

import lombok.Getter;

public class MemberReqDTO {

    @Getter
    public static class SignUpDto {
        private String email;
        private String password;
        private String name;
        private String phoneNumber;
    }

    @Getter
    public static class LoginDto {
        private String email;
        private String password;
    }

    @Getter
    public static class UpdateMemberDto {
        private String name;
        private String password;
        private String phoneNumber;
    }

    @Getter
    public static class WithdrawDto {
        private String password;
    }
}