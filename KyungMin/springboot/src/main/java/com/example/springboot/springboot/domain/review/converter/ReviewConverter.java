package com.example.springboot.springboot.domain.review.converter;

import com.example.springboot.springboot.domain.review.dto.ReviewResDTO;
import com.example.springboot.springboot.domain.review.entity.Review;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewConverter {

    public static ReviewResDTO.ReviewInfoDto toReviewInfoDto(Review review) {
        return new ReviewResDTO.ReviewInfoDto(
                review.getId(),
                review.getBook().getId(),
                review.getMember().getId(),
                review.getMember().getName(),
                review.getContent(),
                review.getRating(),
                LocalDateTime.now()
        );
    }

    public static ReviewResDTO.ReviewCursorListDto toReviewCursorListDto(List<Review> reviews, Integer size) {
        boolean hasNext = reviews.size() > size;

        List<Review> resultReviews = hasNext
                ? reviews.subList(0, size)
                : reviews;

        List<ReviewResDTO.ReviewInfoDto> reviewDtos = resultReviews.stream()
                .map(ReviewConverter::toReviewInfoDto)
                .toList();

        Long nextCursor = hasNext && !resultReviews.isEmpty()
                ? resultReviews.get(resultReviews.size() - 1).getId()
                : null;

        return new ReviewResDTO.ReviewCursorListDto(
                reviewDtos,
                nextCursor,
                hasNext
        );
    }
}
