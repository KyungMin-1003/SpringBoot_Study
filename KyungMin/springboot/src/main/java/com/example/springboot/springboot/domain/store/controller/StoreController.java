package com.example.springboot.springboot.domain.store.controller;

import com.example.springboot.springboot.domain.store.dto.StoreResDTO;
import com.example.springboot.springboot.domain.store.service.StoreService;
import com.example.springboot.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.springboot.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ApiResponse<StoreResDTO.StoreListDto> getStores() {
        StoreResDTO.StoreListDto result = storeService.getStores();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @GetMapping("/{storeId}")
    public ApiResponse<StoreResDTO.StoreInfoDto> getStore(
            @PathVariable Long storeId
    ) {
        StoreResDTO.StoreInfoDto result = storeService.getStore(storeId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
