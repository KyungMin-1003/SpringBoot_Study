package com.example.springboot.springboot.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class StoreReqDTO {
    @Getter
    public static class CreateStoreDto {
        @NotBlank(message = "매장 이름은 필수입니다.")
        @Size(max = 100, message = "매장 이름은 100자 이하여야 합니다.")
        private String name;

        @NotBlank(message = "매장 주소는 필수입니다.")
        @Size(max = 255, message = "매장 주소는 255자 이하여야 합니다.")
        private String address;

        @Size(max = 30, message = "전화번호는 30자 이하여야 합니다.")
        private String phoneNumber;

        @Size(max = 100, message = "영업시간은 100자 이하여야 합니다.")
        private String openingHours;
    }

    @Getter
    public static class UpdateStoreDto {
        private String name;
        private String address;
        private String phoneNumber;
        private String openingHours;
    }
}
