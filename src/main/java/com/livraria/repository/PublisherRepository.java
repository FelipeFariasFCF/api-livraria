package com.livraria.repository;

import com.livraria.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Publisher> findByNameIgnoreCase(String name);
}