package com.example.springboot.springboot.domain.review.controller;

import com.example.springboot.springboot.domain.review.dto.ReviewReqDTO;
import com.example.springboot.springboot.domain.review.dto.ReviewResDTO;
import com.example.springboot.springboot.domain.review.service.ReviewService;
import com.example.springboot.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.springboot.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/books/{bookId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResultDto> createReview(
            @PathVariable Long bookId,
            @Valid @RequestBody ReviewReqDTO.CreateReviewDto request
    ) {
        ReviewResDTO.CreateReviewResultDto result = reviewService.createReview(bookId, request);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 도서 리뷰 목록 조회
    @GetMapping("/books/{bookId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewListDto> getBookReviews(
            @PathVariable Long bookId,

            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "페이지는 0 이상이어야 합니다.")
            Integer page,

            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
            @Max(value = 100, message = "페이지 크기는 100 이하여야 합니다.")
            Integer size
    ) {
        ReviewResDTO.ReviewListDto result =
                reviewService.getBookReviews(bookId, page, size);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 회원 리뷰 목록 조회
    @GetMapping("/members/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewCursorListDto> getMemberReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long cursor,

            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
            @Max(value = 100, message = "페이지 크기는 100 이하여야 합니다.")
            Integer size
    ) {
        ReviewResDTO.ReviewCursorListDto result =
                reviewService.getMyReviews(memberId, cursor, size);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 리뷰 수정
    @PatchMapping("/reviews/{reviewId}")
    public ApiResponse<ReviewResDTO.UpdateReviewResultDto> updateReview(
            @PathVariable Long reviewId,
            @Valid @RequestBody ReviewReqDTO.UpdateReviewDto request
    ) {
        ReviewResDTO.UpdateReviewResultDto result =
                reviewService.updateReview(reviewId, request);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 리뷰 삭제
    @DeleteMapping("/reviews/{reviewId}")
    public ApiResponse<ReviewResDTO.DeleteReviewResultDto> deleteReview(
            @PathVariable Long reviewId
    ) {
        ReviewResDTO.DeleteReviewResultDto result =
                reviewService.deleteReview(reviewId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
