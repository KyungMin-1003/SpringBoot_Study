package com.example.springboot.springboot.domain.store.controller;

import com.example.springboot.springboot.domain.store.dto.StoreResDTO;
import com.example.springboot.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.springboot.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    @GetMapping
    public ApiResponse<StoreResDTO.StoreListDto> getStores() {
        StoreResDTO.StoreInfoDto store1 =
                new StoreResDTO.StoreInfoDto(
                        1L,
                        "Gangnam Store",
                        "Seoul Gangnam-gu"
                );

        StoreResDTO.StoreInfoDto store2 =
                new StoreResDTO.StoreInfoDto(
                        2L,
                        "Hongdae Store",
                        "Seoul Mapo-gu"
                );

        StoreResDTO.StoreListDto result =
                new StoreResDTO.StoreListDto(
                        List.of(store1, store2)
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @GetMapping("/{storeId}")
    public ApiResponse<StoreResDTO.StoreInfoDto> getStore(
            @PathVariable Long storeId
    ) {
        StoreResDTO.StoreInfoDto result =
                new StoreResDTO.StoreInfoDto(
                        storeId,
                        "Gangnam Store",
                        "Seoul Gangnam-gu"
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
