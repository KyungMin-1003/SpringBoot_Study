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
                .build();
    }

    public static MemberResDTO.SignUpResultDto toSignUpResultDto(Member member) {
        return new MemberResDTO.SignUpResultDto(
                member.getId(),
                member.getName(),
                member.getEmail()
        );
    }
}

// 솔직히 컨버터 이거 잘 이해한감  service에 들어갈 수 있는 내용을 따로 분리한거