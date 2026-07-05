package com.example.springboot.springboot.domain.review.repository;

import com.example.springboot.springboot.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(
            value = "SELECT r FROM Review r WHERE r.book.id = :bookId ORDER BY r.id DESC",
            countQuery = "SELECT count(r) FROM Review r WHERE r.book.id = :bookId"
    )
    Page<Review> findReviewsByBookId(@Param("bookId") Long bookId, Pageable pageable);

    @Query(
            value = "SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.id DESC",
            countQuery = "SELECT count(r) FROM Review r WHERE r.member.id = :memberId"
    )
    Page<Review> findReviewsByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    @Query("""
        SELECT r FROM Review r
        WHERE r.member.id = :memberId
        AND (:cursor IS NULL OR r.id < :cursor)
        ORDER BY r.id DESC
        """)
    List<Review> findMyReviewsByCursor(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

}
