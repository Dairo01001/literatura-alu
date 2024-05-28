package com.dairodev.literatura.repository;

import com.dairodev.literatura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByLanguagesContainsIgnoreCase(String code);

    List<Book> findTop10ByOrderByDownloadCountDesc();
}
