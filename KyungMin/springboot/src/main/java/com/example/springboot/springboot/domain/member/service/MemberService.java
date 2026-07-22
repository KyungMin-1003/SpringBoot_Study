package com.example.springboot.springboot.domain.member.service;


import com.example.springboot.springboot.domain.member.converter.MemberConverter;
import com.example.springboot.springboot.domain.member.dto.MemberReqDTO;
import com.example.springboot.springboot.domain.member.dto.MemberResDTO;
import com.example.springboot.springboot.domain.member.entity.Member;
import com.example.springboot.springboot.domain.member.exception.MemberErrorCode;
import com.example.springboot.springboot.domain.member.exception.MemberException;
import com.example.springboot.springboot.domain.member.repository.MemberRepository;
import com.example.springboot.springboot.global.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public MemberResDTO.SignUpResultDto signUp(
            MemberReqDTO.SignUpDto request
    ) {
        // 1. 이메일 중복 확인
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(
                    MemberErrorCode.EMAIL_ALREADY_EXISTS
            );
        }

        // 2. 비밀번호 암호화
        String encodedPassword =
                passwordEncoder.encode(request.getPassword());

        // 3. Member 엔티티 생성
        Member member =
                MemberConverter.toMember(request, encodedPassword);

        // 4. DB 저장
        Member savedMember = memberRepository.save(member);

        // 5. 응답 DTO 반환
        return MemberConverter.toSignUpResultDto(savedMember);
    }
    public MemberResDTO.LoginResultDto login(
            MemberReqDTO.LoginDto request
    ) {
        // 1. 이메일로 회원 조회
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new MemberException(
                                MemberErrorCode.MEMBER_NOT_FOUND
                        )
                );

        // 2. 입력한 비밀번호와 암호화된 비밀번호 비교
        if (!passwordEncoder.matches(
                request.getPassword(),
                member.getPassword()
        )) {
            throw new MemberException(
                    MemberErrorCode.INVALID_PASSWORD
            );
        }

        // 3. 실제 JWT Access Token 생성
        String accessToken =
                jwtTokenProvider.createAccessToken(
                        member.getEmail()
                );

        // 4. 로그인 응답 DTO로 변환
        return MemberConverter.toLoginResultDto(
                member,
                accessToken
        );
    }
    public MemberResDTO.MyInfoDto getMyInfo(
            String email
    ) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() ->
                        new MemberException(
                                MemberErrorCode.MEMBER_NOT_FOUND
                        )
                );

        return MemberConverter.toMyInfoDto(member);
    }
    @Transactional
    public MemberResDTO.UpdateMemberResultDto updateMember(
            String email,
            MemberReqDTO.UpdateMemberDto request
    ) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() ->
                        new MemberException(
                                MemberErrorCode.MEMBER_NOT_FOUND
                        )
                );

        // 이름과 전화번호 변경
        member.updateProfile(
                request.getName(),
                request.getPhoneNumber()
        );

        // 비밀번호가 전달된 경우에만 재암호화
        if (StringUtils.hasText(request.getPassword())) {
            String encodedPassword =
                    passwordEncoder.encode(
                            request.getPassword()
                    );

            member.updatePassword(encodedPassword);
        }

        return new MemberResDTO.UpdateMemberResultDto(
                member.getId(),
                member.getName()
        );
    }
}
