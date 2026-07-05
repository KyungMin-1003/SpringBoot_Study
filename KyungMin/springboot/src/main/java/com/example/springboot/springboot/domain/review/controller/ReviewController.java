package com.example.springboot.springboot.domain.review.controller;

import com.example.springboot.springboot.domain.review.dto.ReviewReqDTO;
import com.example.springboot.springboot.domain.review.dto.ReviewResDTO;
import com.example.springboot.springboot.domain.review.service.ReviewService;
import com.example.springboot.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.springboot.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/books/{bookId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResultDto> createReview(
            @PathVariable Long bookId,
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @RequestBody ReviewReqDTO.CreateReviewDto request
    ) {
        ReviewResDTO.CreateReviewResultDto result = reviewService.createReview(bookId, request);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 도서 리뷰 목록 조회
    @GetMapping("/books/{bookId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewListDto> getBookReviews(
            @PathVariable Long bookId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        ReviewResDTO.ReviewListDto result = reviewService.getBookReviews(bookId, page, size);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 회원 리뷰 목록 조회
    @GetMapping("/members/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewCursorListDto> getMemberReviews(
            @PathVariable Long memberId,
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @RequestParam(required = false) Long cursor,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        ReviewResDTO.ReviewCursorListDto result = reviewService.getMyReviews(memberId, cursor, size);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 리뷰 수정
    @PatchMapping("/reviews/{reviewId}")
    public ApiResponse<ReviewResDTO.UpdateReviewResultDto> updateReview(
            @PathVariable Long reviewId,
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @RequestBody ReviewReqDTO.UpdateReviewDto request
    ) {
        ReviewResDTO.UpdateReviewResultDto result =
                new ReviewResDTO.UpdateReviewResultDto(
                        reviewId,
                        request.getContent()
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 리뷰 삭제
    @DeleteMapping("/reviews/{reviewId}")
    public ApiResponse<ReviewResDTO.DeleteReviewResultDto> deleteReview(
            @PathVariable Long reviewId,
            @RequestHeader(value = "Authorization", required = false) String authorization
    ) {
        ReviewResDTO.DeleteReviewResultDto result =
                new ReviewResDTO.DeleteReviewResultDto(
                        reviewId,
                        "리뷰가 삭제되었습니다."
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
