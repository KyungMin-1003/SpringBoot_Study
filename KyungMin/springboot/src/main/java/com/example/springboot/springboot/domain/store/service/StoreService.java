package com.example.springboot.springboot.domain.store.service;


import com.example.springboot.springboot.domain.store.dto.StoreResDTO;
import com.example.springboot.springboot.domain.store.entity.Store;
import com.example.springboot.springboot.domain.store.exception.StoreErrorCode;
import com.example.springboot.springboot.domain.store.exception.StoreException;
import com.example.springboot.springboot.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    @Transactional(readOnly = true)
    public StoreResDTO.StoreListDto getStores() {
        List<StoreResDTO.StoreInfoDto> stores = storeRepository.findAll().stream()
                .map(store -> new StoreResDTO.StoreInfoDto(
                        store.getId(),
                        store.getName(),
                        store.getAddress()
                ))
                .toList();

        return new StoreResDTO.StoreListDto(stores);
    }

    @Transactional(readOnly = true)
    public StoreResDTO.StoreInfoDto getStore(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() ->
                        new StoreException(
                                StoreErrorCode.STORE_NOT_FOUND
                        )
                );

        return new StoreResDTO.StoreInfoDto(
                store.getId(),
                store.getName(),
                store.getAddress()
        );
    }

}
