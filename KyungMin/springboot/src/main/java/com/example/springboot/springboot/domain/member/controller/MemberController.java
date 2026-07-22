package com.example.springboot.springboot.domain.member.controller;

import com.example.springboot.springboot.domain.member.dto.MemberReqDTO;
import com.example.springboot.springboot.domain.member.dto.MemberResDTO;
import com.example.springboot.springboot.domain.member.service.MemberService;
import com.example.springboot.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.springboot.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignUpResultDto> signUp(
            @Valid @RequestBody MemberReqDTO.SignUpDto request
    ) {
        MemberResDTO.SignUpResultDto result =
                memberService.signUp(request);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                result
        );
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginResultDto> login(
            @Valid @RequestBody MemberReqDTO.LoginDto request
    ) {
        MemberResDTO.LoginResultDto result =
                memberService.login(request);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                result
        );
    }

    // 로그아웃
    @PostMapping("/logout")
    public ApiResponse<MemberResDTO.LogoutResultDto> logout(
            @RequestHeader("Authorization") String authorization
    ) {
        MemberResDTO.LogoutResultDto result =
                new MemberResDTO.LogoutResultDto(
                        "로그아웃되었습니다."
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 내 정보 조회
    @GetMapping("/me")
    public ApiResponse<MemberResDTO.MyInfoDto> getMyInfo(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        MemberResDTO.MyInfoDto result =
                memberService.getMyInfo(
                        userDetails.getUsername()
                );

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                result
        );
    }

    // 회원 정보 수정
    @PatchMapping("/me")
    public ApiResponse<MemberResDTO.UpdateMemberResultDto> updateMember(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody MemberReqDTO.UpdateMemberDto request
    ) {
        MemberResDTO.UpdateMemberResultDto result =
                memberService.updateMember(
                        userDetails.getUsername(),
                        request
                );

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                result
        );
    }

    // 회원 탈퇴
    @DeleteMapping("/me")
    public ApiResponse<MemberResDTO.WithdrawResultDto> withdraw(
            @RequestHeader("Authorization") String authorization,
            @RequestBody MemberReqDTO.WithdrawDto request
    ) {
        MemberResDTO.WithdrawResultDto result =
                new MemberResDTO.WithdrawResultDto(
                        "회원 탈퇴가 완료되었습니다."
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

}

