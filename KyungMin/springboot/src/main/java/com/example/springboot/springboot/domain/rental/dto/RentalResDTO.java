package com.example.springboot.springboot.domain.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class RentalResDTO {
    @Getter
    @AllArgsConstructor
    public static class CreateRentalResultDto {
        private Long rentalId;
        private Long bookId;
        private Long memberId;
        private LocalDateTime rentedAt;
        private LocalDateTime dueDate;
    }

    @Getter
    @AllArgsConstructor
    public static class ReturnRentalResultDto {
        private Long rentalId;
        private Long bookId;
        private LocalDateTime returnedAt;
        private String message;
    }

    @Getter
    @AllArgsConstructor
    public static class RentalInfoDto {
        private Long rentalId;
        private Long bookId;
        private String bookTitle;
        private LocalDateTime rentedAt;
        private LocalDateTime dueDate;
        private LocalDateTime returnedAt;
        private String status;
    }

    @Getter
    @AllArgsConstructor
    public static class RentalListDto {
        private List<RentalInfoDto> rentals;
        private String pageInfo;
    }
    //기존 DTO = 전체 대여 목록용 간단 응답
    //새 DTO = 07주차 미션 조건에 맞춘 페이지네이션 응답

    //기존 API를 변경하지 않기 위해서 새 DTO 분리 미션과 기존 API의 내용이 다르기때문에 새로 만듦
    // 현재 대여중인 목록 조회 응답 DTO
    @Getter
    @AllArgsConstructor
    public static class OngoingRentalListDto {
        private List<RentalInfoDto> rentals;
        private Integer page;
        private Integer size;
        private Integer totalPages;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
