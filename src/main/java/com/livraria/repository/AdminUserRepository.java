package com.livraria.repository;

import com.livraria.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {

    UserDetails findByLogin(String login);
}