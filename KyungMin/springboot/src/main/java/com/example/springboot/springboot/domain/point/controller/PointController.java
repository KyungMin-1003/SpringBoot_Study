package com.example.springboot.springboot.domain.point.controller;

import com.example.springboot.springboot.domain.point.dto.PointReqDTO;
import com.example.springboot.springboot.domain.point.dto.PointResDTO;
import com.example.springboot.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.springboot.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members/{memberId}/points")
public class PointController {

    // 포인트 조회
    @GetMapping
    public ApiResponse<PointResDTO.PointInfoDto> getPoint(
            @PathVariable Long memberId,
            @RequestHeader("Authorization") String authorization
    ) {
        PointResDTO.PointInfoDto result =
                new PointResDTO.PointInfoDto(
                        memberId,
                        25
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 포인트 지급
    @PostMapping("/reward")
    public ApiResponse<PointResDTO.RewardPointResultDto> rewardPoint(
            @PathVariable Long memberId,
            @RequestHeader("Authorization") String authorization,
            @RequestBody PointReqDTO.RewardPointDto request
    ) {
        PointResDTO.RewardPointResultDto result =
                new PointResDTO.RewardPointResultDto(
                        memberId,
                        request.getRewardPoint(),
                        30,
                        "포인트가 지급되었습니다."
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 포인트 사용
    @PostMapping("/use")
    public ApiResponse<PointResDTO.UsePointResultDto> usePoint(
            @PathVariable Long memberId,
            @RequestHeader("Authorization") String authorization,
            @RequestBody PointReqDTO.UsePointDto request
    ) {
        PointResDTO.UsePointResultDto result =
                new PointResDTO.UsePointResultDto(
                        memberId,
                        request.getUsedPoint(),
                        20,
                        "포인트가 사용되었습니다."
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}