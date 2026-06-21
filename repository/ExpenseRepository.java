package repository;

import src.main.java.com.abhilash.splitwise.entity.Expense;

import java.util.List;

public interface ExpenseRepository {
    void save(Expense expense);
    Expense findBy(String expenseId);
    List<Expense> findAll();
}
