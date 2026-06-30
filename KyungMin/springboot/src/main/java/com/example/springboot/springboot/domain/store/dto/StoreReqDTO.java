package com.example.springboot.springboot.domain.store.dto;

import lombok.Getter;

public class StoreReqDTO {
    @Getter
    public static class CreateStoreDto {
        private String name;
        private String address;
        private String phoneNumber;
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
