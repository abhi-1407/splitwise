package com.abhilash.splitwise.service;

import com.abhilash.splitwise.entity.User;
import com.abhilash.splitwise.exception.InvalidDebtException;
import com.abhilash.splitwise.exception.UserNotFoundException;
import com.abhilash.splitwise.repository.BalanceRepository;
import com.abhilash.splitwise.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final UserRepository userRepository;

    public BalanceService(BalanceRepository balanceRepository, UserRepository userRepository) {
        this.balanceRepository = balanceRepository;
        this.userRepository = userRepository;
    }

    public Map<String, Map<String, Long>> getAllBalances() {
        return balanceRepository.getAllBalances();
    }

    public Map<String, Long> getUserBalances(String userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        return balanceRepository.getAllBalances().getOrDefault(userId, Collections.emptyMap());
    }

    public String settleBalance(String debtorId, String creditorId, long amount) {
        User debtor = userRepository.findById(debtorId);
        User creditor = userRepository.findById(creditorId);

        if (debtor == null) {
            throw new UserNotFoundException(debtorId);
        }

        if (creditor == null) {
            throw new UserNotFoundException(creditorId);
        }

        long existingDebt = balanceRepository.getBalance(creditor, debtor);

        if (existingDebt < amount) {
            throw new InvalidDebtException();
        }

        long remainingAmount = existingDebt - amount;

        if (remainingAmount == 0) {
            balanceRepository.removeEntry(creditor, debtor);
        } else {
            balanceRepository.setBalance(creditor, debtor, remainingAmount);
        }

        return "Balance settled successfully";
    }
}