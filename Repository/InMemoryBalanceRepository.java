package Repository;

import entities.User;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class InMemoryBalanceRepository implements BalanceRepository{
    Map<String, Map<String, Long>> balances = new HashMap<>();
    @Override
    public void updateBalance(User creditor, User debtor, long amount) {
        String creditorId = creditor.getId();
        String debtorId = debtor.getId();
        if(!balances.containsKey(creditorId)){
            balances.put(creditorId,new HashMap<>());
        }
        long existingAmount = 0;
        Map<String,Long> debtorMap = balances.get(creditorId);
        if(debtorMap.containsKey(debtorId)){
            existingAmount = debtorMap.get(debtorId);
        }
        debtorMap.put(debtorId,existingAmount + amount);
    }

    @Override
    public long getBalance(User creditor, User debtor) {
        String creditorId = creditor.getId();
        String debtorId = debtor.getId();
        if(balances.containsKey(creditorId)) {
            if(balances.get(creditorId).containsKey(debtorId)){
                return balances.get(creditorId).get(debtorId);
            }
        }
        return 0;
    }
}
