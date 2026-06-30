package com.example.springboot.springboot.domain.rental.controller;

import com.example.springboot.springboot.domain.rental.dto.RentalReqDTO;
import com.example.springboot.springboot.domain.rental.dto.RentalResDTO;
import com.example.springboot.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.springboot.global.apiPayload.code.GeneralSuccessCode;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class RentalController {
    //도서대여
    @PostMapping("/books/{bookId}/rentals")
    public ApiResponse<RentalResDTO.CreateRentalResultDto> rentBook(
            @PathVariable Long bookId,
            @RequestHeader("Authorization") String authorization,
            @RequestBody RentalReqDTO.CreateRentalDto request
    ) {
        RentalResDTO.CreateRentalResultDto result =
                new RentalResDTO.CreateRentalResultDto(
                        1L,
                        bookId,
                        request.getMemberId(),
                        LocalDateTime.now(),
                        LocalDateTime.now().plusDays(14)
                );

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
                new RentalResDTO.ReturnRentalResultDto(
                        rentalId,
                        1L,
                        LocalDateTime.now(),
                        "도서반납 완료"
                );
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,result);
    }

    @GetMapping("/members/{memberId}/rentals")
    public ApiResponse<RentalResDTO.RentalListDto> getMemberRentals(
            @PathVariable Long memberId,
            @RequestHeader("Authorization") String authorization,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        RentalResDTO.RentalInfoDto rental1 =
                new RentalResDTO.RentalInfoDto(
                        1L,
                        1L,
                        "ㅇㄴㄻㅇㄴㄹㅇㅁㄴ",
                        LocalDateTime.now().minusDays(3),
                        LocalDateTime.now().plusDays(11),
                        null,
                        "안녕"
                );

        RentalResDTO.RentalInfoDto rental2 =
                new RentalResDTO.RentalInfoDto(
                        2L,
                        2L,
                        "책 이름",
                        LocalDateTime.now().minusDays(20),
                        LocalDateTime.now().minusDays(6),
                        LocalDateTime.now().minusDays(5),
                        "상태"
                );

        RentalResDTO.RentalListDto result =
                new RentalResDTO.RentalListDto(
                        List.of(rental1, rental2),
                        "page=" + page + ", size=" + size
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}




