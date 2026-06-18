package repository;

import entities.User;

import java.util.Map;

public interface BalanceRepository {
    void updateBalance(User creditor,User debtor,long amount);
    long getBalance(User creditor,User debtor);
    Map<String, Map<String,Long>> getAllBalances();
}
