package com.example.springboot.springboot.domain.member.entity;

import com.example.springboot.springboot.domain.member.enums.Gender;
import com.example.springboot.springboot.domain.member.enums.UserState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private Gender gender = Gender.NONE;

    private LocalDate birthDate;

    @Column(length = 100)
    private String address;

    @Builder.Default
    private Integer point = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private UserState userState = UserState.ACTIVE;
}