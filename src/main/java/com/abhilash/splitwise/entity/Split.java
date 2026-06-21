package com.abhilash.splitwise.entity;

public class Split {
    private final User user;
    private double amount;

    public Split(User user, double share) {
        this.user = user;
        this.amount = share;
    }

    public double getAmount() {
        return amount;
    }

    public User getUser() {
        return user;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }
}
