package com.abhilash.splitwise.controller;

import com.abhilash.splitwise.dto.CreateExpenseRequest;
import com.abhilash.splitwise.dto.ExpenseResponse;
import com.abhilash.splitwise.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @PostMapping
    public ResponseEntity<String> createExpense(@RequestBody @Valid CreateExpenseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.createExpense(request));
    }
    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> getExpense(@PathVariable String expenseId) {
        return ResponseEntity.ok(expenseService.getExpense(expenseId));
    }
}