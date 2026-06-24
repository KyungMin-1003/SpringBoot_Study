package com.example.springboot.springboot.domain.review.dto;

import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class CreateReviewDto {
        private Long memberId;
        private String content;
        private Integer rating;
    }

    @Getter
    public static class UpdateReviewDto {
        private String content;
        private Integer rating;
    }
}
