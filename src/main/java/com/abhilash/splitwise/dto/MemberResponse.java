package com.abhilash.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberResponse {

    private String userId;
    private String name;
    private String email;
}