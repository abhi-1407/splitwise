package com.abhilash.splitwise.controller;

import com.abhilash.splitwise.dto.CreateExpenseRequest;
import com.abhilash.splitwise.entity.Expense;
import com.abhilash.splitwise.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @PostMapping
    public String createExpense(@RequestBody @Valid CreateExpenseRequest request) {
        return expenseService.createExpense(request);
    }
    @GetMapping("/{expenseId}")
    public Expense getExpense(@PathVariable String expenseId) {
        return expenseService.getExpense(expenseId);
    }
}