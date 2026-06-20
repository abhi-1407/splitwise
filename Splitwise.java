import entities.*;
import repository.*;
import service.*;
import strategy.EqualExpenseSplitter;
import strategy.ExactExpenseSplitter;
import strategy.PercentageExpenseSplit;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Splitwise {
    public static void main(String[] args) {
        BalanceRepository balanceRepository = new InMemoryBalanceRepository();
        UserRepository userRepository = new InMemoryUserRepository();

        BalanceService balanceService = new BalanceService(balanceRepository, userRepository);
        UserService userService = new UserService(userRepository);
        DebtSimplificationService debtSimplificationService = new DebtSimplificationService(balanceRepository);

        User user1 = new User("1", "Abhilash", "a@gmail.com");
        User user2 = new User("2", "Rahul", "rl@gmail.com");
        User user3 = new User("3", "Abhishek", "ab@gmail.com");
        User user4 = new User("4", "Mirang", "mn@gmail.com");
        User user5 = new User("5", "Harinder", "h@gmail.com");
        User user6 = new User("6", "Sahil", "sa@gmail.com");

        userService.registerUser(user1);
        userService.registerUser(user2);
        userService.registerUser(user3);
        userService.registerUser(user4);
        userService.registerUser(user5);
        userService.registerUser(user6);

        balanceRepository.updateBalance(user2, user1, 500);
        balanceRepository.updateBalance(user3, user1, 300);
        balanceRepository.updateBalance(user3, user4, 200);
        balanceRepository.updateBalance(user5, user4, 400);
        balanceRepository.updateBalance(user6, user2, 250);
        balanceRepository.updateBalance(user6, user5, 350);
        balanceService.showAllBalances();

        System.out.println("-------------------");

        //Settling balances
        List<Settlement> result = debtSimplificationService.getSimplifiedBalance();

        for(Settlement settlement : result){
            System.out.println(userRepository.findById(settlement.getDebtor()).getName() + " -> " + userRepository.findById(settlement.getCreditor()).getName() + " = " + settlement.getAmount());
        }
    }
}