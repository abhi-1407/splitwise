package com.abhilash.splitwise.repository;

import com.abhilash.splitwise.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmailId(String emailId);
}