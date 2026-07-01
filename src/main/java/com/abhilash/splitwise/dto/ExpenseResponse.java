package com.abhilash.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ExpenseResponse {

    private String expenseId;

    private double amount;

    private String paidByUserId;

    private String paidByName;

    private String groupId;

    private String groupName;

    private List<SplitResponse> splits;
}