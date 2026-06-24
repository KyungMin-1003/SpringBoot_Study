package com.example.springboot.springboot.domain.member.controller;

import com.example.springboot.springboot.domain.member.dto.MemberReqDTO;
import com.example.springboot.springboot.domain.member.dto.MemberResDTO;
import com.example.springboot.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.springboot.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    //회원가입
    @PostMapping("/auth/signup")
    public ApiResponse<MemberResDTO.SignUpResultDto> signUp(
            @RequestBody MemberReqDTO.SignUpDto request
            ) {
        MemberResDTO.SignUpResultDto result =
                new MemberResDTO.SignUpResultDto(
                        1L,
                        request.getLoginId(),
                        request.getName()
                );
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    //로그인
    @PostMapping("/auth/login")
    public ApiResponse<MemberResDTO.LoginResultDto> login(
            @RequestBody MemberReqDTO.LoginDto request
    ) {
        MemberResDTO.LoginResultDto result =
                new MemberResDTO.LoginResultDto(
                        1L,
                        "temporary-access-token"
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }


    //탈퇴
    @DeleteMapping("/member/me")
    public ApiResponse<MemberResDTO.WithdrawResultDto> withdraw(
            @RequestHeader("Authorization") String authorization,
            @RequestBody MemberReqDTO.WithdrawDto request
    ) {
        MemberResDTO.WithdrawResultDto result =
                new MemberResDTO.WithdrawResultDto("회원 탈퇴가 완료되었습니다.");

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}




