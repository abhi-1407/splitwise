package repository;

import src.main.java.com.abhilash.splitwise.entity.User;

public interface UserRepository {
    void save(User user);
    User findById(String userId);
}
