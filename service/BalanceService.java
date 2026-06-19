package service;

import repository.BalanceRepository;
import repository.UserRepository;
import entities.User;

import java.util.Map;

public class BalanceService {
    private final BalanceRepository balanceRepository;
    private final UserRepository userRepository;
    public BalanceService(BalanceRepository balanceRepository, UserRepository userRepository){
        this.balanceRepository = balanceRepository;
        this.userRepository = userRepository;
    }
    public void showBalance(User user){
        Map<String, Map<String,Long>> balances = balanceRepository.getAllBalances();
        String userId = user.getId();
        String userName = user.getName();
        Map<String,Long> debtorMap = balances.get(userId);
        if(debtorMap.isEmpty()){
            return;
        }
        for(String s : debtorMap.keySet()){
            System.out.println(userRepository.findById(s).getName() + " owes amount " + debtorMap.get(s) + " to " + userName);
        }
    }

    public void showAllBalances(){
        Map<String, Map<String,Long>> balances = balanceRepository.getAllBalances();

        for(String userId : balances.keySet()){
            Map<String,Long> debtorMap = balances.get(userId);
            for(String s : debtorMap.keySet()){
                System.out.println(userRepository.findById(s).getName() + " owes amount " + debtorMap.get(s) + " to " + userRepository.findById(userId).getName());
            }
        }

    }
}
