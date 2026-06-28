package com.abhilash.splitwise.service;

import com.abhilash.splitwise.model.NetBalance;
import com.abhilash.splitwise.model.Settlement;
import com.abhilash.splitwise.repository.BalanceRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DebtSimplificationService {

    private final BalanceRepository balanceRepository;

    public DebtSimplificationService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    private Map<String, Long> getNetBalances(Map<String, Map<String, Long>> balances) {

        Map<String, Long> netBalances = new HashMap<>();
        for (String creditorId : balances.keySet()) {
            for (String debtorId : balances.get(creditorId).keySet()) {
                long amount = balances.get(creditorId).get(debtorId);
                netBalances.put(creditorId, netBalances.getOrDefault(creditorId, 0L) + amount);
                netBalances.put(debtorId, netBalances.getOrDefault(debtorId, 0L) - amount);
            }
        }
        return netBalances;
    }

    public List<Settlement> getSimplifiedBalance() {

        PriorityQueue<NetBalance> creditors = new PriorityQueue<>((a, b) -> Long.compare(b.getAmount(), a.getAmount()));
        PriorityQueue<NetBalance> debtors = new PriorityQueue<>((a, b) -> Long.compare(b.getAmount(), a.getAmount()));

        Map<String, Map<String, Long>> balances = balanceRepository.getAllBalances();
        Map<String, Long> netBalances = getNetBalances(balances);
        List<Settlement> settlements = new ArrayList<>();

        for (Map.Entry<String, Long> entry : netBalances.entrySet()) {
            long amount = entry.getValue();
            if (amount > 0) {
                creditors.add(new NetBalance(entry.getKey(), amount));
            } else if (amount < 0) {
                debtors.add(new NetBalance(entry.getKey(), Math.abs(amount)));
            }
        }

        while (!creditors.isEmpty() && !debtors.isEmpty()) {
            NetBalance creditor = creditors.poll();
            NetBalance debtor = debtors.poll();

            long settlementAmount = Math.min(creditor.getAmount(), debtor.getAmount());

            settlements.add(new Settlement(debtor.getUserId(), creditor.getUserId(), settlementAmount));
            creditor.setAmount(creditor.getAmount() - settlementAmount);

            debtor.setAmount(debtor.getAmount() - settlementAmount);

            if (creditor.getAmount() > 0) {
                creditors.add(new NetBalance(creditor.getUserId(), creditor.getAmount()));
            }

            if (debtor.getAmount() > 0) {
                debtors.add(new NetBalance(debtor.getUserId(), debtor.getAmount()));
            }
        }

        return settlements;
    }
}