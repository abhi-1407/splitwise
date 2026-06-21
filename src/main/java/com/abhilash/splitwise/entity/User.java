package com.abhilash.splitwise.entity;


import lombok.Getter;

@Getter
public class User {
    private final String id;
    private final String name;
    private final String emailId;

    public User(String id,String name,String emailId){
        this.id = id;
        this.name = name;
        this.emailId = emailId;
    }
}
