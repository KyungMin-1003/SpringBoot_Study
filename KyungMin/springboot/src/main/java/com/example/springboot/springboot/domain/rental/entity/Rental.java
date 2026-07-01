package com.example.springboot.springboot.domain.rental.entity;

import com.example.springboot.springboot.domain.book.entity.Book;
import com.example.springboot.springboot.domain.member.entity.Member;
import com.example.springboot.springboot.domain.rental.enums.RentalStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private LocalDateTime rentedAt;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    private LocalDateTime returnedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private RentalStatus status = RentalStatus.RENTED;

    @Column(length = 255)
    private String returnMemo;

    public void returnBook(String memo) {
        this.returnedAt = LocalDateTime.now();
        this.status = RentalStatus.RETURNED;
        this.returnMemo = memo;
    }
}
