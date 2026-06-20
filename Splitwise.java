import entities.Group;
import repository.*;
import service.BalanceService;
import service.ExpenseService;
import service.GroupService;
import service.UserService;
import strategy.EqualExpenseSplitter;
import strategy.ExactExpenseSplitter;
import strategy.PercentageExpenseSplit;
import entities.PercentageSplit;
import entities.Split;
import entities.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Splitwise {
    public static void main(String[] args) {
        ExpenseRepository expenseRepository = new InMemoryExpenseRepository();
        BalanceRepository balanceRepository = new InMemoryBalanceRepository();
        UserRepository userRepository = new InMemoryUserRepository();
        GroupRepository groupRepository = new InMemoryGroupRepository();

        ExpenseService expenseService = new ExpenseService(expenseRepository, balanceRepository);
        BalanceService balanceService = new BalanceService(balanceRepository, userRepository);
        UserService userService = new UserService(userRepository);
        GroupService groupService = new GroupService(groupRepository);

        User user1 = new User("1", "Abhilash", "a@gmail.com");
        User user2 = new User("2", "Rahul", "rl@gmail.com");
        User user3 = new User("3", "Abhishek", "ab@gmail.com");

        userService.registerUser(user1);
        userService.registerUser(user2);
        userService.registerUser(user3);

        Set<User> groupMembers = new HashSet<>();
        groupMembers.add(user1);
        groupMembers.add(user2);
        groupMembers.add(user3);

        Group group = new Group("1","Germany Trip",groupMembers);

        List<Split> splitList = List.of(
                new Split(user1,100),
                new Split(user2,200),
                new Split(user3,500)
        );

        expenseService.createGroupExpense("EXP-1",800,user1,splitList,new ExactExpenseSplitter(),group);
        balanceService.showAllBalances();
        System.out.println("-------------------");

        //Settling balances
        balanceService.settleBalance(user2,user1,100);

        balanceService.showAllBalances();
    }
}