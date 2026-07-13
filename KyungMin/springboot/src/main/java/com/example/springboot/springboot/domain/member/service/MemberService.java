package com.example.springboot.springboot.domain.member.service;


import com.example.springboot.springboot.domain.member.converter.MemberConverter;
import com.example.springboot.springboot.domain.member.dto.MemberReqDTO;
import com.example.springboot.springboot.domain.member.dto.MemberResDTO;
import com.example.springboot.springboot.domain.member.entity.Member;
import com.example.springboot.springboot.domain.member.exception.MemberErrorCode;
import com.example.springboot.springboot.domain.member.exception.MemberException;
import com.example.springboot.springboot.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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
}
