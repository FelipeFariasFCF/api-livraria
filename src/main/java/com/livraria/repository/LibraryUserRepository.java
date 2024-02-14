package com.livraria.repository;

import com.livraria.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {
}