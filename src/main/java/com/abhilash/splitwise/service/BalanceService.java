package com.abhilash.splitwise.service;

import com.abhilash.splitwise.entity.Expense;
import com.abhilash.splitwise.entity.Group;
import com.abhilash.splitwise.entity.Split;
import com.abhilash.splitwise.exception.GroupNotFoundException;
import com.abhilash.splitwise.exception.UserNotFoundException;
import com.abhilash.splitwise.repository.ExpenseRepository;
import com.abhilash.splitwise.repository.GroupRepository;
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
    private final GroupRepository groupRepository;

    public BalanceService(
            ExpenseRepository expenseRepository,
            UserRepository userRepository,
            GroupRepository groupRepository) {

        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public Map<String, Map<String, Long>> getAllBalances() {
        return computeBalances(expenseRepository.findAll());
    }

    public Map<String, Map<String, Long>> getGroupBalances(String groupId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));

        return computeBalances(expenseRepository.findByGroup(group));
    }

    public Map<String, Long> getUserBalances(String userId) {

        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Map<String, Long> result = new HashMap<>();

        Map<String, Map<String, Long>> balances = getAllBalances();

        for (String creditor : balances.keySet()) {

            for (Map.Entry<String, Long> entry : balances.get(creditor).entrySet()) {

                String debtor = entry.getKey();
                long amount = entry.getValue();

                if (creditor.equals(userId)) {
                    result.put(debtor, amount);
                }

                if (debtor.equals(userId)) {
                    result.put(creditor, -amount);
                }
            }
        }

        return result;
    }

    private Map<String, Map<String, Long>> computeBalances(List<Expense> expenses) {

        Map<String, Map<String, Long>> balances = new HashMap<>();

        for (Expense expense : expenses) {

            String creditor = expense.getPaidBy().getId();

            for (Split split : expense.getSplits()) {

                String debtor = split.getUser().getId();

                if (creditor.equals(debtor))
                    continue;

                long amount = (long) split.getAmount();

                long reverse = balances
                        .getOrDefault(debtor, Collections.emptyMap())
                        .getOrDefault(creditor, 0L);

                if (reverse > 0) {

                    if (reverse > amount) {

                        balances.get(debtor)
                                .put(creditor, reverse - amount);

                    } else if (reverse == amount) {

                        balances.get(debtor)
                                .remove(creditor);

                    } else {

                        balances.get(debtor)
                                .remove(creditor);

                        balances.computeIfAbsent(creditor, k -> new HashMap<>())
                                .merge(debtor, amount - reverse, Long::sum);
                    }

                } else {

                    balances.computeIfAbsent(creditor, k -> new HashMap<>())
                            .merge(debtor, amount, Long::sum);
                }
            }
        }

        return balances;
    }
}