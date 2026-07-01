package com.abhilash.splitwise.exception;

public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException(String expenseId) {
        super("Expense not found with id : " + expenseId);
    }
}