package com.abhilash.splitwise.model;

import lombok.Getter;

@Getter
public class Settlement {
    private final String debtor;
    private final String creditor;
    private final long amount;

    public Settlement(String debtor,String creditor,long amount){
        this.debtor = debtor;
        this.creditor = creditor;
        this.amount = amount;
    }

}
