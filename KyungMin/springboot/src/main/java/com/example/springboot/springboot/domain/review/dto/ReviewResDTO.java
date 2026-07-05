package com.example.springboot.springboot.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Getter
    @AllArgsConstructor
    public static class CreateReviewResultDto {
        private Long reviewId;
        private Long bookId;
        private Long memberId;
        private LocalDateTime createdAt;
    }

    @Getter
    @AllArgsConstructor
    public static class ReviewInfoDto {
        private Long reviewId;
        private Long bookId;
        private Long memberId;
        private String memberName;
        private String content;
        private Integer rating;
        private LocalDateTime createdAt;
    }

    @Getter
    @AllArgsConstructor
    public static class ReviewListDto {
        private List<ReviewInfoDto> reviews;
        private String pageInfo;
    }

    @Getter
    @AllArgsConstructor
    public static class UpdateReviewResultDto {
        private Long reviewId;
        private String content;
    }

    @Getter
    @AllArgsConstructor
    public static class DeleteReviewResultDto {
        private Long reviewId;
        private String message;
    }

    @Getter
    @AllArgsConstructor
    public static class ReviewCursorListDto {
        private List<ReviewInfoDto> reviews;
        private Long nextCursor;
        private Boolean hasNext;
    }
}