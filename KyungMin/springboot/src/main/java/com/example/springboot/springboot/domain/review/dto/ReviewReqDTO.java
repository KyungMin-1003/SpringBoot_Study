package com.example.springboot.springboot.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;

public class ReviewReqDTO {

    @Getter
    public static class CreateReviewDto {

        @NotNull
        private Long memberId;

        @NotBlank
        private String content;

        @NotNull
        @Min(value = 1, message = "rating must be greater than or equal to 1.")
        @Max(value = 5, message = "rating must be less than or equal to 5.")
        private Integer rating;
    }

    @Getter
    public static class UpdateReviewDto {
        private String content;
        private Integer rating;
    }
}
