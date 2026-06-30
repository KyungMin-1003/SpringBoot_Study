package com.example.springboot.springboot.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class StoreResDTO {

    @Getter
    @AllArgsConstructor
    public static class StoreInfoDto {
        private Long storeId;
        private String name;
        private String address;
    }

    @Getter
    @AllArgsConstructor
    public static class StoreListDto {
        private List<StoreInfoDto> stores;
    }
}
