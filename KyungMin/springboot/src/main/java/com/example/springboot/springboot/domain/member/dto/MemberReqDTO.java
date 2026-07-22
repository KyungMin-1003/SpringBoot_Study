package com.example.springboot.springboot.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class MemberReqDTO {

    @Getter
    public static class SignUpDto {

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
        private String password;

        @NotBlank(message = "이름은 필수입니다.")
        @Size(min = 2, max = 20, message = "이름은 2자 이상 20자 이하여야 합니다.")
        private String name;

        private String phoneNumber;
    }

    @Getter
    public static class LoginDto {

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;
    }

    @Getter
    public static class UpdateMemberDto {

        @Size(
                min = 2,
                max = 20,
                message = "이름은 2자 이상 20자 이하여야 합니다."
        )
        private String name;

        @Size(
                min = 8,
                max = 20,
                message = "비밀번호는 8자 이상 20자 이하여야 합니다."
        )
        private String password;

        @Size(
                max = 30,
                message = "전화번호는 30자를 초과할 수 없습니다."
        )
        private String phoneNumber;
    }

    @Getter
    public static class WithdrawDto {
        private String password;
    }
}
