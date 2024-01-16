package com.livraria.repository;

import com.livraria.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByTitleIgnoreCase(String name);

    Optional<Book> findByTitleIgnoreCase(String name);
}