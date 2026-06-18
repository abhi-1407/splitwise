package Repository;

import entities.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryBalanceRepository implements BalanceRepository{
    Map<String, Map<String, Long>> balances = new HashMap<>();
    @Override
    public void updateBalance(User creditor, User debtor, long amount) {
        String creditorId = creditor.getId();
        String debtorId = debtor.getId();
        long alreadyOwedAmount = 0;

        if(balances.containsKey(debtorId) && balances.get(debtorId).containsKey(creditorId)){
            alreadyOwedAmount = balances.get(debtorId).get(creditorId);
        }

        long reverseBalance = amount - alreadyOwedAmount;

        /*
           If finalSettledAmount is positive - then that means that debtor ows money to creditor
           If finalSettledAmount is negative - then that means that creditor ows money to debtor
           If finalSettledAmount is zero - then that means the money is balanced
        */

        if(reverseBalance > 0){
            setAmount(creditor,debtor,reverseBalance);
            removeEntry(debtor,creditor);
        }else if(reverseBalance == 0){
            removeEntry(creditor,debtor);
            removeEntry(debtor,creditor);
        }else{
            setAmount(debtor,creditor,Math.abs(reverseBalance));
        }
    }

    private void removeEntry(User creditor,User debtor){
        String creditorId = creditor.getId();
        String debtorId = debtor.getId();
        if(!balances.containsKey(creditorId) || !balances.get(creditorId).containsKey(debtorId)){
            return;
        }
        balances.get(creditorId).remove(debtorId);
    }

    private void setAmount(User creditor,User debtor,long amount){
        String creditorId = creditor.getId();
        String debtorId = debtor.getId();

        if(!balances.containsKey(creditorId)){
            balances.put(creditorId,new HashMap<>());
        }
        Map<String,Long> debtorMap = balances.get(creditorId);
        debtorMap.put(debtorId, amount);
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

    @Override
    public Map<String, Map<String, Long>> getAllBalances() {
        return balances;
    }

}
