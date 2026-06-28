package com.abhilash.splitwise.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BalanceNode {
    private final String userId;
    private long amount;

    public BalanceNode(String userId,long amount){
        this.amount = amount;
        this.userId = userId;
    }
}
