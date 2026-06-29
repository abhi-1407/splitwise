package com.abhilash.splitwise.service;

import com.abhilash.splitwise.entity.Expense;
import com.abhilash.splitwise.entity.Split;
import com.abhilash.splitwise.exception.UserNotFoundException;
import com.abhilash.splitwise.repository.ExpenseRepository;
import com.abhilash.splitwise.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BalanceService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public BalanceService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    /**
     * Returns:
     *
     * creditorId
     *      ↓
     * debtorId -> amount
     */
    public Map<String, Map<String, Long>> getAllBalances() {

        List<Expense> expenses = expenseRepository.findAll();
        Map<String, Map<String, Long>> balances = new HashMap<>();

        for (Expense expense : expenses) {
            String creditorId = expense.getPaidBy().getId();

            for (Split split : expense.getSplits()) {
                String debtorId = split.getUser().getId();
                if (creditorId.equals(debtorId)) {
                    continue;
                }

                long amount = (long) split.getAmount();
                balances.computeIfAbsent(creditorId, k -> new HashMap<>()).merge(debtorId, amount, Long::sum);
            }
        }
        return balances;
    }

    public Map<String, Long> getUserBalances(String userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return getAllBalances().getOrDefault(userId, Collections.emptyMap());
    }
}