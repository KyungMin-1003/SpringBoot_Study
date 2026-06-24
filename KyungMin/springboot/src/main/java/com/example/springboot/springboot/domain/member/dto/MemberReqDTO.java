package com.example.springboot.springboot.domain.member.dto;

import lombok.Getter;

public class MemberReqDTO {

    @Getter
    public  static class  SignUpDto{
        private String loginId;
        private String password;
        private String name;
        private String phoneNumber;
        private String email;
    }

    @Getter
    public static class LoginDto {
        private String loginId;
        private String password;
    }

    @Getter
    public static  class WithdrawDto{
        private String password;
    }


}
