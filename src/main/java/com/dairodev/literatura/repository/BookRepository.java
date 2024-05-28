package com.dairodev.literatura.repository;

import com.dairodev.literatura.models.Author;
import com.dairodev.literatura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
