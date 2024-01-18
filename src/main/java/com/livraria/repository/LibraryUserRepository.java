package com.livraria.repository;

import com.livraria.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {
    boolean existsByEmailIgnoreCase(String email);

    Optional<LibraryUser> findByEmailIgnoreCase(String email);
}