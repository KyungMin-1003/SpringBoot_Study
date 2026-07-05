package com.example.springboot.springboot.domain.rental.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class RentalReqDTO {

    @Getter
    public static class CreateRentalDto{
        private Long memberId;
    }

    @Getter
    public static class ReturnRentalDto{
        private String memo;
    }

    //현재 진행중인 주제인 도서대여 서비스에 맞춰서 진행중인 미션을 현재 대여 중인 도서 목록으로 대체
    @Getter
    public static class OngoingRentalSearchDto {
        @NotNull(message = "memberId is required.")
        private Long memberId;

        @NotNull(message = "page is required.")
        @Min(value = 0, message = "page must be greater than or equal to 0.")
        private Integer page = 0;

        @NotNull(message = "size is required.")
        @Min(value = 1, message = "size must be greater than or equal to 1.")
        private Integer size = 10;
    }
}
