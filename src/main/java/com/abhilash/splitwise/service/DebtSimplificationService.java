package com.abhilash.splitwise.service;

import com.abhilash.splitwise.entity.Expense;
import com.abhilash.splitwise.entity.Split;
import com.abhilash.splitwise.model.NetBalance;
import com.abhilash.splitwise.model.Settlement;
import com.abhilash.splitwise.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class DebtSimplificationService {

    private final ExpenseRepository expenseRepository;

    public DebtSimplificationService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Settlement> getSimplifiedBalance() {

        List<Expense> expenses = expenseRepository.findAll();

        Map<String, Long> netBalances = new HashMap<>();
        for (Expense expense : expenses) {
            String creditorId = expense.getPaidBy().getId();
            for (Split split : expense.getSplits()) {
                String debtorId = split.getUser().getId();
                if (creditorId.equals(debtorId)) {
                    continue;
                }
                long amount = (long) split.getAmount();
                netBalances.put(creditorId, netBalances.getOrDefault(creditorId, 0L) + amount);
                netBalances.put(debtorId, netBalances.getOrDefault(debtorId, 0L) - amount);
            }
        }

        PriorityQueue<NetBalance> creditors = new PriorityQueue<>((a, b) -> Long.compare(b.getAmount(), a.getAmount()));
        PriorityQueue<NetBalance> debtors = new PriorityQueue<>((a, b) -> Long.compare(b.getAmount(), a.getAmount()));

        for (Map.Entry<String, Long> entry : netBalances.entrySet()) {

            if (entry.getValue() > 0) {
                creditors.add(new NetBalance(entry.getKey(), entry.getValue()));
            } else if (entry.getValue() < 0) {
                debtors.add(new NetBalance(entry.getKey(), Math.abs(entry.getValue())));
            }
        }

        List<Settlement> settlements = new ArrayList<>();
        while (!creditors.isEmpty() && !debtors.isEmpty()) {

            NetBalance creditor = creditors.poll();
            NetBalance debtor = debtors.poll();

            long settlementAmount = Math.min(creditor.getAmount(), debtor.getAmount());
            settlements.add(new Settlement(debtor.getUserId(), creditor.getUserId(), settlementAmount));
            creditor.setAmount(creditor.getAmount() - settlementAmount);
            debtor.setAmount(debtor.getAmount() - settlementAmount);

            if (creditor.getAmount() > 0) {
                creditors.add(creditor);
            }

            if (debtor.getAmount() > 0) {
                debtors.add(debtor);
            }
        }
        return settlements;

    }

}