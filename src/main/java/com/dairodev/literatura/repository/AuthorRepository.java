package com.dairodev.literatura.repository;

import com.dairodev.literatura.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AuthorRepository extends JpaRepository<Author, String> {

    Set<Author> findByDeathYearLessThanEqual(int year);
}
