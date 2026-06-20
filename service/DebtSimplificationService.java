package service;

import entities.BalanceNode;
import entities.Settlement;
import repository.BalanceRepository;

import java.util.*;

public class DebtSimplificationService {
    private final BalanceRepository balanceRepository;

    public DebtSimplificationService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    private Map<String, Long> getNetBalances(Map<String, Map<String, Long>> balances) {
        Map<String, Long> balancesMap = new HashMap<>();
        for (String creditorId : balances.keySet()) {
            for (String debtorId : balances.get(creditorId).keySet()) {
                long amount = balances.get(creditorId).get(debtorId);
                balancesMap.put(creditorId,balancesMap.getOrDefault(creditorId,0L) +  amount);
                balancesMap.put(debtorId, balancesMap.getOrDefault(debtorId,0L) -  amount);
            }
        }
        return balancesMap;
    }

    public List<Settlement> getSimplifiedBalance() {
        PriorityQueue<BalanceNode> debtors = new PriorityQueue<>((a, b) -> Long.compare(b.getAmount(), a.getAmount()));
        PriorityQueue<BalanceNode> creditors = new PriorityQueue<>((a, b) -> Long.compare(b.getAmount(), a.getAmount()));
        Map<String, Map<String, Long>> balances = balanceRepository.getAllBalances();
        Map<String, Long> netBalances = getNetBalances(balances);
        List<Settlement> simplifiedBalances = new ArrayList<>();
        for (String entry : netBalances.keySet()) {
            if (netBalances.get(entry) < 0) {
                debtors.add(new BalanceNode(entry, Math.abs(netBalances.get(entry))));
            } else {
                creditors.add(new BalanceNode(entry, netBalances.get(entry)));
            }
        }

        while(!creditors.isEmpty() && !debtors.isEmpty()){
            BalanceNode creditor = creditors.poll();//getting money
            BalanceNode debtor = debtors.poll();//giving money

            long settleAmount = Math.min(creditor.getAmount(),debtor.getAmount());

            simplifiedBalances.add(new Settlement(debtor.getUserId(),creditor.getUserId(),settleAmount));
            creditor.setAmount(creditor.getAmount() - settleAmount);
            debtor.setAmount(debtor.getAmount() - settleAmount);

            if(creditor.getAmount() > 0){
                creditors.add(new BalanceNode(creditor.getUserId(), creditor.getAmount()));
            }

            if(debtor.getAmount() > 0){
                debtors.add(new BalanceNode(debtor.getUserId(), debtor.getAmount()));
            }
        }
        return simplifiedBalances;
    }
};
