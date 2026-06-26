package com.abhilash.splitwise.dto;

import com.abhilash.splitwise.repository.UserRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserResponse {
    public UserResponse(String id,String email,String name){
        this.id = id;
        this.email = email;
        this.name = name;
    }
    @NotBlank
    private String id;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String name;
}
