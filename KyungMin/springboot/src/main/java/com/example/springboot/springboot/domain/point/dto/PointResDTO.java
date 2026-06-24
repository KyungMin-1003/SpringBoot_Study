package com.example.springboot.springboot.domain.point.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class PointResDTO {

    @Getter
    @AllArgsConstructor
    public static class PointInfoDto {
        private Long memberId;
        private Integer point;
    }

    @Getter
    @AllArgsConstructor
    public static class RewardPointResultDto {
        private Long memberId;
        private Integer rewardPoint;
        private Integer totalPoint;
        private String message;
    }

    @Getter
    @AllArgsConstructor
    public static class UsePointResultDto {
        private Long memberId;
        private Integer usedPoint;
        private Integer remainingPoint;
        private String message;
    }
}