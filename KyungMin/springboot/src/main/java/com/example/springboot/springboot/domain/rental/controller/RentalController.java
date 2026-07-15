package com.example.springboot.springboot.domain.rental.controller;

import com.example.springboot.springboot.domain.rental.dto.RentalReqDTO;
import com.example.springboot.springboot.domain.rental.dto.RentalResDTO;
import com.example.springboot.springboot.domain.rental.service.RentalService;
import com.example.springboot.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.springboot.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
public class RentalController {
    private final RentalService rentalService;

    //도서대여
    @PostMapping("/books/{bookId}/rentals")
    public ApiResponse<RentalResDTO.CreateRentalResultDto> rentBook(
            @PathVariable Long bookId,
            @Valid @RequestBody RentalReqDTO.CreateRentalDto request
    ) {
        RentalResDTO.CreateRentalResultDto result =
                rentalService.createRental(bookId, request);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }


    //도서 반납
    @PatchMapping("/rentals/{rentalId}/return")
    public ApiResponse<RentalResDTO.ReturnRentalResultDto> returnBook(
            @PathVariable Long rentalId,
            @Valid @RequestBody RentalReqDTO.ReturnRentalDto request
    ) {
        RentalResDTO.ReturnRentalResultDto result =
                rentalService.returnRental(rentalId, request);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @GetMapping("/members/{memberId}/rentals")
    public ApiResponse<RentalResDTO.RentalListDto> getMemberRentals(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "페이지는 0 이상이어야 합니다.")
            Integer page,

            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
            @Max(value = 100, message = "페이지 크기는 100 이하여야 합니다.")
            Integer size
    ) {
        RentalResDTO.RentalListDto result =
                rentalService.getMemberRentals(memberId, page, size);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    //기존 API는 회원의 전체 대여 이력을 조회하는 API로 유지했고,
    //07주차 필수 요구사항인 “진행중인 미션 조회는 별도 API로 구현
    //조회 성격의 API이지만 과제에 사용자 ID를 Request Body로 받아야 해서 POST로 구현함
    @PostMapping("/rentals/ongoing")
    public ApiResponse<RentalResDTO.OngoingRentalListDto> getOngoingRentals(
            @Valid @RequestBody RentalReqDTO.OngoingRentalSearchDto request
    ) {
        RentalResDTO.OngoingRentalListDto result =
                rentalService.getOngoingRentals(request);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}




