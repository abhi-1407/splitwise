package com.abhilash.splitwise.dto;

import com.abhilash.splitwise.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreateGroupRequest {
    @NotBlank
    private String groupName;

    @NotEmpty
    private Set<User> members;
}
