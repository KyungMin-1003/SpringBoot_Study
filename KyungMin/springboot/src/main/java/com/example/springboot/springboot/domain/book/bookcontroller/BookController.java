package com.example.springboot.springboot.domain.book.bookcontroller;

import com.example.springboot.springboot.domain.book.bookdto.BookReqDTO;
import com.example.springboot.springboot.domain.book.bookdto.BookResDTO;
import com.example.springboot.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.springboot.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    //도서 등록
    @PostMapping
    public ApiResponse<BookResDTO.CreateBookResultDto> createBook(
            @RequestHeader("Authorization") String authorization,
            @RequestBody BookReqDTO.CreateBookDto request
    ) {
        BookResDTO.CreateBookResultDto result =
                new BookResDTO.CreateBookResultDto(
                        1L,
                        request.getTitle()
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 도서 목록 조회
    @GetMapping
    public ApiResponse<BookResDTO.BookListDto> getBookList(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category
    ) {
        BookResDTO.BookInfoDto book1 =
                new BookResDTO.BookInfoDto(
                        1L,
                        "자바의 정석",
                        "ㄴㅇㅁ",
                        "23",
                        "123",
                        true
                );

        BookResDTO.BookInfoDto book2 =
                new BookResDTO.BookInfoDto(
                        2L,
                        "12",
                        "13",
                        "14",
                        "15",
                        false
                );

        BookResDTO.BookListDto result =
                new BookResDTO.BookListDto(
                        List.of(book1, book2),
                        "page" + page + ", size" + size
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

//도서 상세 조회
    @GetMapping("/{bookId}")
    public ApiResponse<BookResDTO.BookInfoDto> getBook(
            @PathVariable Long bookId
    ) {
        BookResDTO.BookInfoDto result =
                new BookResDTO.BookInfoDto(
                        bookId,
                        "23",
                        "12",
                        "13",
                        "123",
                        true
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    //도서 정보 수정
    @PatchMapping("/{bookId}")
    public ApiResponse<BookResDTO.UpdateBookResultDto> updateBook(
            @PathVariable Long bookId,
            @RequestHeader("Authorization") String authorization,
            @RequestBody BookReqDTO.UpdateBookDto request
    ) {
        BookResDTO.UpdateBookResultDto result =
                new BookResDTO.UpdateBookResultDto(
                        bookId,
                        request.getTitle()
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

 //도서 삭제
    @DeleteMapping("/{bookId}")
    public ApiResponse<BookResDTO.DeleteBookResultDto> deleteBook(
            @PathVariable Long bookId,
            @RequestHeader("Authorization") String authorization
    ) {
        BookResDTO.DeleteBookResultDto result =
                new BookResDTO.DeleteBookResultDto(
                        bookId,
                        "도서가 삭제되었습니다."
                );

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
