package repository;

import entities.Expense;

import java.util.List;

public interface ExpenseRepository {
    void save(Expense expense);
    Expense findBy(String expenseId);
    List<Expense> findAll();
}
