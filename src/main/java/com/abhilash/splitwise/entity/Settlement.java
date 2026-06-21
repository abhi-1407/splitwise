package com.abhilash.splitwise.entity;

public class Settlement {
    private final String debtor;
    private final String creditor;
    private final long amount;

    public Settlement(String debtor,String creditor,long amount){
        this.debtor = debtor;
        this.creditor = creditor;
        this.amount = amount;
    }

    public long getAmount(){
        return amount;
    }
    public String getDebtor(){
        return debtor;
    };
    public String getCreditor(){
        return creditor;
    };

}
