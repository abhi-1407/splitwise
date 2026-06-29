package com.abhilash.splitwise.service;

import com.abhilash.splitwise.dto.CreateExpenseRequest;
import com.abhilash.splitwise.dto.SplitRequest;
import com.abhilash.splitwise.entity.Expense;
import com.abhilash.splitwise.entity.Split;
import com.abhilash.splitwise.entity.User;
import com.abhilash.splitwise.exception.UserNotFoundException;
import com.abhilash.splitwise.repository.ExpenseRepository;
import com.abhilash.splitwise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public String createExpense(CreateExpenseRequest request) {
        User paidBy = userRepository.findById(request.getPaidByUserId()).orElseThrow(() -> new UserNotFoundException(request.getPaidByUserId()));

        Expense expense = new Expense();
        expense.setAmount(request.getAmount());
        expense.setPaidBy(paidBy);

        List<Split> splits = new ArrayList<>();

        for (SplitRequest splitRequest : request.getSplits()) {
            User user = userRepository.findById(splitRequest.getUserId()).orElseThrow(() -> new UserNotFoundException(splitRequest.getUserId()));

            Split split = new Split();
            split.setAmount(splitRequest.getAmount());
            split.setUser(user);
            split.setExpense(expense);
            splits.add(split);
        }

        expense.setSplits(splits);
        expenseRepository.save(expense);
        return "Expense created successfully";
    }

    public Expense getExpense(String expenseId) {
        return expenseRepository.findById(expenseId).orElseThrow(() -> new RuntimeException("Expense not found"));
    }
}