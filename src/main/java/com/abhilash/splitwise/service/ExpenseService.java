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

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public String createExpense(CreateExpenseRequest request) {
        User paidBy = userRepository.findById(request.getPaidByUserId());

        if (paidBy == null) {
            throw new UserNotFoundException(request.getPaidByUserId());
        }

        List<Split> splits = new ArrayList<>();

        for (SplitRequest splitRequest : request.getSplits()) {
            User user = userRepository.findById(splitRequest.getUserId());
            if (user == null) {
                throw new UserNotFoundException(splitRequest.getUserId());
            }
            splits.add(new Split(user, splitRequest.getAmount()));
        }

        Expense expense = new Expense(request.getExpenseId(), request.getAmount(), paidBy, splits, null);

        expenseRepository.save(expense);

        return "Expense created successfully";
    }

    public Expense getExpense(String expenseId) {
        return expenseRepository.findBy(expenseId);
    }
}