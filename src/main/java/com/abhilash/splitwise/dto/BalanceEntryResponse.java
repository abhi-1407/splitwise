package com.abhilash.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BalanceEntryResponse {

    private String userId;
    private String userName;
    private long amount;
}