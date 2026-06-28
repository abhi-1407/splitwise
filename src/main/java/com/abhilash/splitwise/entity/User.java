package com.abhilash.splitwise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String emailId;

    @OneToMany(mappedBy = "paidBy")
    private List<Expense> paidExpenses = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Split> splits = new ArrayList<>();

    @ManyToMany(mappedBy = "members")
    private Set<Group> groups = new HashSet<>();
}