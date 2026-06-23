package com.abhilash.splitwise.repository;


import com.abhilash.splitwise.entity.Expense;

import java.util.List;

public interface ExpenseRepository {
    void save(Expense expense);
    Expense findBy(String expenseId);
    List<Expense> findAll();
}
