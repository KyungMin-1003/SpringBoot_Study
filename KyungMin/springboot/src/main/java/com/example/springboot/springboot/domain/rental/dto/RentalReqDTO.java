package com.example.springboot.springboot.domain.rental.dto;

import lombok.Getter;

import java.time.LocalDateTime;

public class RentalReqDTO {

    @Getter
    public static class CreateRentalDto{
        private Long memberId;
    }

    @Getter
    public static class ReturnRentalDto{
        private String memo;
    }

}
