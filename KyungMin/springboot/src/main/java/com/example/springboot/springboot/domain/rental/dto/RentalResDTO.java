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
}
