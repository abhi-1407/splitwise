package com.abhilash.splitwise.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NetBalance {
    private final String userId;
    private long amount;

    public NetBalance(String userId, long amount){
        this.amount = amount;
        this.userId = userId;
    }
}
