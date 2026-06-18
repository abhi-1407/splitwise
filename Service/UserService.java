package Service;

import Repository.UserRepository;
import entities.User;

public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public void registerUser(User user){
        userRepository.save(user);
    }
    public User getUser(String userId){
        return userRepository.findById(userId);
    }
}
