package com.abhilash.splitwise.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettleBalanceRequest {
    private String debtorId;
    private String creditorId;
    private String groupId;
    private long amount;
}