package com.example.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prueba.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByOrigin(String origin);
}
