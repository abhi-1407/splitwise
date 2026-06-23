package com.abhilash.splitwise.repository;


import com.abhilash.splitwise.entity.Expense;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryExpenseRepository implements ExpenseRepository{
    private final Map<String,Expense> expenseMap = new HashMap<>();
    @Override
    public void save(Expense expense) {
        expenseMap.put(expense.getExpenseId(),expense);
    }

    @Override
    public Expense findBy(String expenseId) {
        return expenseMap.getOrDefault(expenseId,null);
    }

    @Override
    public List<Expense> findAll() {
        return new ArrayList<>(expenseMap.values());
    }
}
