import Repository.*;
import Service.BalanceService;
import Service.ExpenseService;
import Service.UserService;
import Strategy.EqualExpenseSplitter;
import Strategy.ExpenseSplitter;
import Strategy.PercentageExpenseSplit;
import entities.PercentageSplit;
import entities.Split;
import entities.User;

import java.util.List;

public class Splitwise {
    public static void main(String[] args) {
        ExpenseRepository expenseRepository = new InMemoryExpenseRepository();
        BalanceRepository balanceRepository = new InMemoryBalanceRepository();
        UserRepository userRepository = new InMemoryUserRepository();

        ExpenseService expenseService = new ExpenseService(expenseRepository, balanceRepository);
        BalanceService balanceService = new BalanceService(balanceRepository, userRepository);
        UserService userService = new UserService(userRepository);

        User user1 = new User("1", "Abhilash", "a@gmail.com");
        User user2 = new User("2", "Rahul", "r@gmail.com");

        userService.registerUser(user1);
        userService.registerUser(user2);


        List<Split> splitList = List.of(
                new PercentageSplit(user1,0,20),
                new PercentageSplit(user2,0,80)
        );

        expenseService.createExpense("EXP-1",900,user1,splitList,new PercentageExpenseSplit());
        balanceService.showBalance(user1);
    }
}