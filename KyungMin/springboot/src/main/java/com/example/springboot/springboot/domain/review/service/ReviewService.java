package com.example.springboot.springboot.domain.review.service;


import com.example.springboot.springboot.domain.book.entity.Book;
import com.example.springboot.springboot.domain.book.repository.BookRepository;
import com.example.springboot.springboot.domain.member.entity.Member;
import com.example.springboot.springboot.domain.member.repository.MemberRepository;
import com.example.springboot.springboot.domain.review.converter.ReviewConverter;
import com.example.springboot.springboot.domain.review.dto.ReviewReqDTO;
import com.example.springboot.springboot.domain.review.dto.ReviewResDTO;
import com.example.springboot.springboot.domain.review.entity.Review;
import com.example.springboot.springboot.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    @Transactional
    public ReviewResDTO.CreateReviewResultDto createReview(Long bookId, ReviewReqDTO.CreateReviewDto request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다."));

        Review review = Review.builder()
                .member(member)
                .book(book)
                .content(request.getContent())
                .rating(request.getRating())
                .build();

        Review savedReview = reviewRepository.save(review);

        return new ReviewResDTO.CreateReviewResultDto(
                savedReview.getId(),
                book.getId(),
                member.getId(),
                LocalDateTime.now()
        );
    }

    @Transactional
    public ReviewResDTO.ReviewListDto getBookReviews(Long bookId, Integer page, Integer size) {
        Page<Review> reviewPage = reviewRepository.findReviewsByBookId(
                bookId,
                PageRequest.of(page, size)
        );

        List<ReviewResDTO.ReviewInfoDto> reviews = reviewPage.getContent().stream()
                .map(ReviewConverter::toReviewInfoDto)
                .toList();

        return new ReviewResDTO.ReviewListDto(
                reviews,
                "page=" + page + ", size=" + size +
                        ", totalPages=" + reviewPage.getTotalPages() +
                        ", totalElements=" + reviewPage.getTotalElements()
        );
    }

    @Transactional
    public ReviewResDTO.ReviewListDto getMemberReviews(Long memberId, Integer page, Integer size) {
        Page<Review> reviewPage = reviewRepository.findReviewsByMemberId(
                memberId,
                PageRequest.of(page, size)
        );

        List<ReviewResDTO.ReviewInfoDto> reviews = reviewPage.getContent().stream()
                .map(ReviewConverter::toReviewInfoDto)
                .toList();

        return new ReviewResDTO.ReviewListDto(
                reviews,
                "page=" + page + ", size=" + size +
                        ", totalPages=" + reviewPage.getTotalPages() +
                        ", totalElements=" + reviewPage.getTotalElements()
        );
    }

    @Transactional
    public ReviewResDTO.ReviewCursorListDto getMyReviews(Long memberId, Long cursor, Integer size) {
        List<Review> reviews = reviewRepository.findMyReviewsByCursor(
                memberId,
                cursor,
                PageRequest.of(0, size + 1)
        );

        return ReviewConverter.toReviewCursorListDto(reviews, size);
    }
}
