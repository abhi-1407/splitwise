package com.abhilash.splitwise.repository;


import com.abhilash.splitwise.entity.User;

public interface UserRepository {
    void save(User user);
    User findById(String userId);
}
