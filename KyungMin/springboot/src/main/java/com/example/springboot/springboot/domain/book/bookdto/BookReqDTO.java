package com.example.springboot.springboot.domain.book.bookdto;

import lombok.Getter;

public class BookReqDTO {

    @Getter
    public static class CreateBookDto {
        private String title;
        private String author;
        private String publisher;
        private String category;
    }

    @Getter
    public static class UpdateBookDto {
        private String title;
        private String author;
        private String publisher;
        private String category;
    }
}