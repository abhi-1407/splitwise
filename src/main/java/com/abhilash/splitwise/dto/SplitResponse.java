package com.abhilash.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SplitResponse {

    private String userId;

    private String userName;

    private double amount;
}