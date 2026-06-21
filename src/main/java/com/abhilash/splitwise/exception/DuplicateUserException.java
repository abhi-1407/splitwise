package com.abhilash.splitwise.exception;

public class DuplicateUserException extends RuntimeException{
    public DuplicateUserException(){
        super("User already exists");
    }
}
