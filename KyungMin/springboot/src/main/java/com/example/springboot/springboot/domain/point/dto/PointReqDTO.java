package com.example.springboot.springboot.domain.point.dto;

import lombok.Getter;

public class PointReqDTO {

    @Getter
    public static class RewardPointDto {
        private Integer rewardPoint;
        private String reason;
    }

    @Getter
    public static class UsePointDto {
        private Integer usedPoint;
        private String storeName;
    }
}