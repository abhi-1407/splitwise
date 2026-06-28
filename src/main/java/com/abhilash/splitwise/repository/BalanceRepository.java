package com.abhilash.splitwise.repository;


import com.abhilash.splitwise.entity.BalanceNode;
import com.abhilash.splitwise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

public interface BalanceRepository extends JpaRepository<BalanceNode,String> {
    void updateBalance(User creditor, User debtor, long amount);
    long getBalance(User creditor,User debtor);
    void setBalance(User creditor,User debtor,long amount);
    void removeEntry(User creditor,User debtor);
    Map<String, Map<String,Long>> getAllBalances();
}
