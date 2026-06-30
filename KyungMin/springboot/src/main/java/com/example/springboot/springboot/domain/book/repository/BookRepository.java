package com.example.springboot.springboot.domain.book.repository;

import com.example.springboot.springboot.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
