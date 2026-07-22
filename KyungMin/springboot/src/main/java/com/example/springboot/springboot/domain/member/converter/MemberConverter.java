package com.example.springboot.springboot.domain.member.converter;

import com.example.springboot.springboot.domain.member.dto.MemberReqDTO;
import com.example.springboot.springboot.domain.member.dto.MemberResDTO;
import com.example.springboot.springboot.domain.member.entity.Member;

public class MemberConverter {

    public static Member toMember(
            MemberReqDTO.SignUpDto request,
            String encodedPassword
    ) {
        return Member.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    public static MemberResDTO.SignUpResultDto toSignUpResultDto(Member member) {
        return new MemberResDTO.SignUpResultDto(
                member.getId(),
                member.getName(),
                member.getEmail()
        );
    }
    public static MemberResDTO.LoginResultDto toLoginResultDto(
            Member member,
            String accessToken
    ) {
        return new MemberResDTO.LoginResultDto(
                accessToken,
                member.getId(),
                member.getName()
        );
    }
    public static MemberResDTO.MyInfoDto toMyInfoDto(
            Member member
    ) {
        return new MemberResDTO.MyInfoDto(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getPoint()
        );
    }

}

// 솔직히 컨버터 이거 잘 이해한감  service에 들어갈 수 있는 내용을 따로 분리한거