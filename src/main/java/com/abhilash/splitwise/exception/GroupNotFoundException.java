package com.abhilash.splitwise.exception;

public class GroupNotFoundException extends RuntimeException{
    public GroupNotFoundException(String userId){
        super("Group not found: " + userId);
    }
}
