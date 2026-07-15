package com.example.springboot.springboot.domain.review.entity;


import com.example.springboot.springboot.domain.book.entity.Book;
import com.example.springboot.springboot.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public void updateReview(String content, Integer rating) {
        this.content = content;
        this.rating = rating;
    }
    public void update(String content, Integer rating) {
        this.content = content;
        this.rating = rating;
    }
}
