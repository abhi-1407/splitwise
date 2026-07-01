package com.abhilash.splitwise.service;

import com.abhilash.splitwise.dto.CreateExpenseRequest;
import com.abhilash.splitwise.dto.ExpenseResponse;
import com.abhilash.splitwise.dto.SplitRequest;
import com.abhilash.splitwise.dto.SplitResponse;
import com.abhilash.splitwise.entity.Expense;
import com.abhilash.splitwise.entity.Group;
import com.abhilash.splitwise.entity.Split;
import com.abhilash.splitwise.entity.User;
import com.abhilash.splitwise.exception.ExpenseNotFoundException;
import com.abhilash.splitwise.exception.GroupNotFoundException;
import com.abhilash.splitwise.exception.UserNotFoundException;
import com.abhilash.splitwise.repository.ExpenseRepository;
import com.abhilash.splitwise.repository.GroupRepository;
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
    private final GroupRepository groupRepository;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            UserRepository userRepository,
            GroupRepository groupRepository) {

        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public String createExpense(CreateExpenseRequest request) {

        User paidBy = userRepository.findById(request.getPaidByUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getPaidByUserId()));

        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new GroupNotFoundException(request.getGroupId()));

        Expense expense = new Expense();
        expense.setAmount(request.getAmount());
        expense.setPaidBy(paidBy);
        expense.setGroup(group);

        List<Split> splits = new ArrayList<>();

        for (SplitRequest splitRequest : request.getSplits()) {

            User user = userRepository.findById(splitRequest.getUserId())
                    .orElseThrow(() -> new UserNotFoundException(splitRequest.getUserId()));

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

    public ExpenseResponse getExpense(String expenseId) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException(expenseId));

        List<SplitResponse> splitResponses = expense.getSplits()
                .stream()
                .map(split -> new SplitResponse(
                        split.getUser().getId(),
                        split.getUser().getName(),
                        split.getAmount()
                ))
                .toList();

        return new ExpenseResponse(
                expense.getExpenseId(),
                expense.getAmount(),
                expense.getPaidBy().getId(),
                expense.getPaidBy().getName(),
                expense.getGroup() != null ? expense.getGroup().getGroupId() : null,
                expense.getGroup() != null ? expense.getGroup().getGroupName() : null,
                splitResponses
        );
    }
}