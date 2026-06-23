package com.abhilash.splitwise.service;

import com.abhilash.splitwise.entity.User;
import com.abhilash.splitwise.exception.DuplicateUserException;
import com.abhilash.splitwise.exception.UserNotFoundException;
import com.abhilash.splitwise.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public void registerUser(User user){
        if(userRepository.findById(user.getId()) != null){
            throw new DuplicateUserException();
        }
        userRepository.save(user);
    }
    public User getUser(String userId){
        User user = userRepository.findById(userId);
        if(user == null){
            throw new UserNotFoundException(userId);
        }
        return userRepository.findById(userId);
    }
}
