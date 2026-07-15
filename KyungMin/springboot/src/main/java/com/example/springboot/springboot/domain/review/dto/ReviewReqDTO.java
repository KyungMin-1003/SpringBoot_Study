package com.example.springboot.springboot.domain.review.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NonNull;

public class ReviewReqDTO {

    @Getter
    public static class CreateReviewDto {

        @NotNull(message = "회원 ID는 필수입니다.")
        @Positive(message = "회원 ID는 양수여야 합니다.")
        private Long memberId;

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        @Size(max = 1000, message = "리뷰 내용은 1000자 이하여야 합니다.")
        private String content;

        @NotNull(message = "평점은 필수입니다.")
        @Min(value = 1, message = "평점은 1 이상이어야 합니다.")
        @Max(value = 5, message = "평점은 5 이하여야 합니다.")
        private Integer rating;
    }

    @Getter
    public static class UpdateReviewDto {

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        @Size(max = 1000, message = "리뷰 내용은 1000자 이하여야 합니다.")
        private String content;

        @NotNull(message = "평점은 필수입니다.")
        @Min(value = 1, message = "평점은 1 이상이어야 합니다.")
        @Max(value = 5, message = "평점은 5 이하여야 합니다.")
        private Integer rating;
    }
}
