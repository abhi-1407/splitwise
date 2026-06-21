package service;

import exceptions.DuplicateUserException;
import repository.UserRepository;
import src.main.java.com.abhilash.splitwise.entity.User;

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
        return userRepository.findById(userId);
    }
}
