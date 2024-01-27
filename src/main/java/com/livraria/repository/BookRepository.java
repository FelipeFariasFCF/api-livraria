package com.livraria.repository;

import com.livraria.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByTitleIgnoreCase(String name);

    Optional<Book> findByTitleIgnoreCase(String name);

    @Query("SELECT b FROM Book b WHERE b.id = :idBook AND b.availableQuantity > 0")
    Optional<Book> findByIdAndAvailableQuantityGreaterThanZero(@Param("idBook") Long idBook);
}