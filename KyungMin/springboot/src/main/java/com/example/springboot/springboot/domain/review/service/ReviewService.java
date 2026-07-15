package com.example.springboot.springboot.domain.review.service;


import com.example.springboot.springboot.domain.book.entity.Book;
import com.example.springboot.springboot.domain.book.exception.BookErrorCode;
import com.example.springboot.springboot.domain.book.exception.BookException;
import com.example.springboot.springboot.domain.book.repository.BookRepository;
import com.example.springboot.springboot.domain.member.entity.Member;
import com.example.springboot.springboot.domain.member.exception.MemberErrorCode;
import com.example.springboot.springboot.domain.member.exception.MemberException;
import com.example.springboot.springboot.domain.member.repository.MemberRepository;
import com.example.springboot.springboot.domain.review.converter.ReviewConverter;
import com.example.springboot.springboot.domain.review.dto.ReviewReqDTO;
import com.example.springboot.springboot.domain.review.dto.ReviewResDTO;
import com.example.springboot.springboot.domain.review.entity.Review;
import com.example.springboot.springboot.domain.review.exception.ReviewErrorCode;
import com.example.springboot.springboot.domain.review.exception.ReviewException;
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
                .orElseThrow(() ->
                        new MemberException(
                                MemberErrorCode.MEMBER_NOT_FOUND
                        )
                );

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new BookException(
                                BookErrorCode.BOOK_NOT_FOUND
                        )
                );

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
    @Transactional
    public ReviewResDTO.UpdateReviewResultDto updateReview(
            Long reviewId,
            ReviewReqDTO.UpdateReviewDto request
    ) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new ReviewException(
                                ReviewErrorCode.REVIEW_NOT_FOUND
                        )
                );

        review.update(request.getContent(), request.getRating());

        return new ReviewResDTO.UpdateReviewResultDto(
                review.getId(),
                review.getContent()
        );
    }

    @Transactional
    public ReviewResDTO.DeleteReviewResultDto deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new ReviewException(
                                ReviewErrorCode.REVIEW_NOT_FOUND
                        )
                );

        reviewRepository.delete(review);

        return new ReviewResDTO.DeleteReviewResultDto(
                reviewId,
                "리뷰가 삭제되었습니다."
        );
    }
}
