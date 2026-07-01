package com.abhilash.splitwise.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BalanceResponse {

    private List<BalanceEntryResponse> owedToMe = new ArrayList<>();

    private List<BalanceEntryResponse> iOwe = new ArrayList<>();

    private long netBalance;
}