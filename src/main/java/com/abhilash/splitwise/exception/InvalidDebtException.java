package com.abhilash.splitwise.exception;

public class InvalidDebtException extends RuntimeException{
    public InvalidDebtException(){
        super("Amount is more than the debt");
    }
}
