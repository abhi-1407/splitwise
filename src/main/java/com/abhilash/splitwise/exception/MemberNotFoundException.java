package com.abhilash.splitwise.exception;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(String userId){
        super("Member not found " + userId);
    }
}
