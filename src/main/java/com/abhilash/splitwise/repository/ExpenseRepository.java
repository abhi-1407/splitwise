package com.abhilash.splitwise.repository;


import com.abhilash.splitwise.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,String> {
    Expense save(Expense expense);
    Expense findBy(String expenseId);
    List<Expense> findAll();
}
