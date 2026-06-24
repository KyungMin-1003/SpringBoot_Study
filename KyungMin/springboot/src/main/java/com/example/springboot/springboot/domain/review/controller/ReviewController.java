package com.example.springboot.springboot.domain.review.controller;

import com.example.springboot.springboot.domain.review.dto.ReviewReqDTO;
import com.example.springboot.springboot.domain.review.dto.ReviewResDTO;
import com.example.springboot.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.springboot.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ReviewController {

    // 독후감 작성
    @PostMapping("/books/{bookId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResultDto> createReview(
            @PathVariable Long bookId,
            @RequestHeader("Authorization") String authorization,
            @RequestBody ReviewReqDTO.CreateReviewDto request
    ) {
        ReviewResDTO.CreateReviewResultDto result =
                new ReviewResDTO.CreateReviewResultDto(
                        1L,
                        bookId,
                        request.getMemberId(),
                        LocalDateTime.now()
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 도서 리뷰 목록 조회
    @GetMapping("/books/{bookId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewListDto> getBookReviews(
            @PathVariable Long bookId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        ReviewResDTO.ReviewInfoDto review1 =
                new ReviewResDTO.ReviewInfoDto(
                        1L,
                        bookId,
                        1L,
                        "김경민",
                        "책이 이해하기 쉽고 좋았습니다.",
                        5,
                        LocalDateTime.now()
                );

        ReviewResDTO.ReviewInfoDto review2 =
                new ReviewResDTO.ReviewInfoDto(
                        2L,
                        bookId,
                        2L,
                        "홍길동",
                        "내용이 유익했습니다.",
                        4,
                        LocalDateTime.now()
                );

        ReviewResDTO.ReviewListDto result =
                new ReviewResDTO.ReviewListDto(
                        List.of(review1, review2),
                        "page=" + page + ", size=" + size
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 회원 리뷰 목록 조회
    @GetMapping("/members/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewListDto> getMemberReviews(
            @PathVariable Long memberId,
            @RequestHeader("Authorization") String authorization,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        ReviewResDTO.ReviewInfoDto review1 =
                new ReviewResDTO.ReviewInfoDto(
                        1L,
                        1L,
                        memberId,
                        "김경민",
                        "책내용 !@#ㅉ@!#!#.",
                        5,
                        LocalDateTime.now()
                );

        ReviewResDTO.ReviewInfoDto review2 =
                new ReviewResDTO.ReviewInfoDto(
                        2L,
                        2L,
                        memberId,
                        "김경민",
                        "굿 좋아용",
                        4,
                        LocalDateTime.now()
                );

        ReviewResDTO.ReviewListDto result =
                new ReviewResDTO.ReviewListDto(
                        List.of(review1, review2),
                        "page=" + page + ", size=" + size
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 독후감 수정
    @PatchMapping("/reviews/{reviewId}")
    public ApiResponse<ReviewResDTO.UpdateReviewResultDto> updateReview(
            @PathVariable Long reviewId,
            @RequestHeader("Authorization") String authorization,
            @RequestBody ReviewReqDTO.UpdateReviewDto request
    ) {
        ReviewResDTO.UpdateReviewResultDto result =
                new ReviewResDTO.UpdateReviewResultDto(
                        reviewId,
                        request.getContent()
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 독후감 삭제
    @DeleteMapping("/reviews/{reviewId}")
    public ApiResponse<ReviewResDTO.DeleteReviewResultDto> deleteReview(
            @PathVariable Long reviewId,
            @RequestHeader("Authorization") String authorization
    ) {
        ReviewResDTO.DeleteReviewResultDto result =
                new ReviewResDTO.DeleteReviewResultDto(
                        reviewId,
                        "독후감이 삭제되었습니다."
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}