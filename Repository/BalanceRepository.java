package Repository;

import entities.User;

public interface BalanceRepository {
    void updateBalance(User creditor,User debtor,long amount);
    long getBalance(User creditor,User debtor);
}
