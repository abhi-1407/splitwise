package com.abhilash.splitwise.dto;

import jakarta.validation.constraints.NotBlank;

public class SplitRequest {

    @NotBlank
    private String userId;

    private double amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}