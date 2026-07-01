package com.abhilash.splitwise.model;

import lombok.AllArgsConstructor;

import lombok.Getter;

import lombok.Setter;

@Getter

@Setter

@AllArgsConstructor

public class SimplifiedSettlement {

    private String debtor;

    private String creditor;

    private long amount;

}