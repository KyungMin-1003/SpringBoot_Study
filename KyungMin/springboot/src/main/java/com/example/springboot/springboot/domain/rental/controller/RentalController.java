package com.example.springboot.springboot.domain.rental.controller;

import com.example.springboot.springboot.domain.rental.dto.RentalReqDTO;
import com.example.springboot.springboot.domain.rental.dto.RentalResDTO;
import com.example.springboot.springboot.domain.rental.service.RentalService;
import com.example.springboot.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.springboot.global.apiPayload.code.GeneralSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
@RestController
public class RentalController {
    private final RentalService rentalService;

    //도서대여
    @PostMapping("/books/{bookId}/rentals")
    public ApiResponse<RentalResDTO.CreateRentalResultDto> rentBook(
            @PathVariable Long bookId,
            @RequestHeader("Authorization") String authorization,
            @RequestBody RentalReqDTO.CreateRentalDto request
    ) {
        RentalResDTO.CreateRentalResultDto result =
                rentalService.createRental(bookId, request);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }


    //도서 반납
    @PatchMapping("/rentals/{rentalId}/return")
    public ApiResponse<RentalResDTO.ReturnRentalResultDto> returnBook(
            @PathVariable Long rentalId,
            @RequestHeader("Authorization") String authorization,
            @RequestBody RentalReqDTO.ReturnRentalDto request
    ) {
        RentalResDTO.ReturnRentalResultDto result =
                rentalService.returnRental(rentalId, request);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @GetMapping("/members/{memberId}/rentals")
    public ApiResponse<RentalResDTO.RentalListDto> getMemberRentals(
            @PathVariable Long memberId,
            @RequestHeader("Authorization") String authorization,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        RentalResDTO.RentalListDto result =
                rentalService.getMemberRentals(memberId, page, size);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}




