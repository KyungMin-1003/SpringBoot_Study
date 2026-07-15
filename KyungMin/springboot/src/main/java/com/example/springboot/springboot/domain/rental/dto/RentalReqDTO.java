package com.example.springboot.springboot.domain.rental.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class RentalReqDTO {

    @Getter
    public static class CreateRentalDto {

        @NotNull(message = "회원 ID는 필수입니다.")
        @Positive(message = "회원 ID는 양수여야 합니다.")
        private Long memberId;
    }

    @Getter
    public static class ReturnRentalDto {

        @Size(max = 255, message = "반납 메모는 255자 이하여야 합니다.")
        private String memo;
    }

    @Getter
    public static class OngoingRentalSearchDto {

        @NotNull(message = "회원 ID는 필수입니다.")
        @Positive(message = "회원 ID는 양수여야 합니다.")
        private Long memberId;

        @NotNull(message = "페이지는 필수입니다.")
        @Min(value = 0, message = "페이지는 0 이상이어야 합니다.")
        private Integer page = 0;

        @NotNull(message = "페이지 크기는 필수입니다.")
        @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
        @Max(value = 100, message = "페이지 크기는 100 이하여야 합니다.")
        private Integer size = 10;
    }
}