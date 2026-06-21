package com.abhilash.splitwise.repository;

import com.abhilash.splitwise.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();
    @Override
    public void save(User user) {
        if(users.containsKey(user.getId())){
            return;
        }
        users.put(user.getId(),user);
    }
    @Override
    public User findById(String userId) {
        if(!users.containsKey(userId)){
                return null;
        }
        return users.get(userId);
    }
}
