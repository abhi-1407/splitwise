package com.abhilash.splitwise.service;

import com.abhilash.splitwise.entity.Expense;
import com.abhilash.splitwise.entity.Group;
import com.abhilash.splitwise.entity.Split;
import com.abhilash.splitwise.exception.GroupNotFoundException;
import com.abhilash.splitwise.model.NetBalance;
import com.abhilash.splitwise.model.Settlement;
import com.abhilash.splitwise.model.SimplifiedSettlement;
import com.abhilash.splitwise.repository.ExpenseRepository;
import com.abhilash.splitwise.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DebtSimplificationService {

    private final ExpenseRepository expenseRepository;
    private final GroupRepository groupRepository;

    public DebtSimplificationService(
            ExpenseRepository expenseRepository,
            GroupRepository groupRepository) {

        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
    }

    public List<SimplifiedSettlement> getSimplifiedBalance() {
        return simplify(expenseRepository.findAll());
    }

    public List<SimplifiedSettlement> getGroupSimplifiedBalance(String groupId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));

        return simplify(expenseRepository.findByGroup(group));
    }

    private List<SimplifiedSettlement> simplify(List<Expense> expenses) {

        Map<String, Long> netBalances = new HashMap<>();

        for (Expense expense : expenses) {

            String creditorId = expense.getPaidBy().getId();

            for (Split split : expense.getSplits()) {

                String debtorId = split.getUser().getId();

                if (creditorId.equals(debtorId)) {
                    continue;
                }

                long splitAmount = (long) split.getAmount();

                netBalances.merge(creditorId, splitAmount, Long::sum);
                netBalances.merge(debtorId, -splitAmount, Long::sum);
            }
        }

        PriorityQueue<NetBalance> creditors =
                new PriorityQueue<>(Comparator.comparingLong(NetBalance::getAmount).reversed());

        PriorityQueue<NetBalance> debtors =
                new PriorityQueue<>(Comparator.comparingLong(NetBalance::getAmount).reversed());

        for (Map.Entry<String, Long> entry : netBalances.entrySet()) {

            if (entry.getValue() > 0) {
                creditors.offer(new NetBalance(entry.getKey(), entry.getValue()));
            } else if (entry.getValue() < 0) {
                debtors.offer(new NetBalance(entry.getKey(), -entry.getValue()));
            }
        }

        List<SimplifiedSettlement> settlements = new ArrayList<>();

        while (!creditors.isEmpty() && !debtors.isEmpty()) {

            NetBalance creditor = creditors.poll();
            NetBalance debtor = debtors.poll();

            long settlementAmount = Math.min(
                    creditor.getAmount(),
                    debtor.getAmount()
            );

            settlements.add(
                    new SimplifiedSettlement(
                            debtor.getUserId(),
                            creditor.getUserId(),
                            settlementAmount
                    )
            );

            creditor.setAmount(creditor.getAmount() - settlementAmount);
            debtor.setAmount(debtor.getAmount() - settlementAmount);

            if (creditor.getAmount() > 0) {
                creditors.offer(creditor);
            }

            if (debtor.getAmount() > 0) {
                debtors.offer(debtor);
            }
        }

        return settlements;
    }
}