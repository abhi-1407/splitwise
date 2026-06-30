package com.abhilash.splitwise.controller;

import com.abhilash.splitwise.dto.CreateUserRequest;
import com.abhilash.splitwise.dto.UserResponse;
import com.abhilash.splitwise.entity.User;
import com.abhilash.splitwise.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/users")
    public String createUser(@Valid @RequestBody CreateUserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setEmailId(request.getEmail());
        userService.registerUser(user);
        return "User successfully registered";
    }
    @GetMapping("/users/{id}")
    public UserResponse getUser(@PathVariable String id){
        User user = userService.getUser(id);
        return new UserResponse(user.getId(),user.getEmailId(), user.getName());
    }
}
