package com.example.springboot.springboot.domain.book.bookdto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class BookResDTO {

    @Getter
    @AllArgsConstructor
    public static class CreateBookResultDto {
        private Long bookId;
        private String title;
    }

    @Getter
    @AllArgsConstructor
    public static class BookInfoDto {
        private Long bookId;
        private String title;
        private String author;
        private String publisher;
        private String category;
        private Boolean isAvailable;
    }

    @Getter
    @AllArgsConstructor
    public static class BookListDto {
        private List<BookInfoDto> books;
        private String pageInfo;
    }

    @Getter
    @AllArgsConstructor
    public static class UpdateBookResultDto {
        private Long bookId;
        private String title;
    }

    @Getter
    @AllArgsConstructor
    public static class DeleteBookResultDto {
        private Long bookId;
        private String message;
    }
}
