package com.abhilash.splitwise.entity;

import jakarta.persistence.*;

@Entity
@Table(name="balances")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private User creditor;

    @ManyToOne
    private User debtor;

    @ManyToOne
    private Group group;
    private long amount;

}
