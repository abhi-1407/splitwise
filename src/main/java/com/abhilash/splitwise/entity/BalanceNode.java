package com.abhilash.splitwise.entity;


public class BalanceNode {
    private final String userId;
    private long amount;

    public BalanceNode(String userId,long amount){
        this.amount = amount;
        this.userId = userId;
    }

    public void setAmount(long amount){
        this.amount = amount;
    }

    public long getAmount(){
        return amount;
    }

    public String getUserId(){
        return userId;
    }
}
