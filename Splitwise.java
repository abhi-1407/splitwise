import repository.*;
import service.*;
import src.main.java.com.abhilash.splitwise.entity.Group;
import src.main.java.com.abhilash.splitwise.entity.Settlement;
import src.main.java.com.abhilash.splitwise.entity.Split;
import src.main.java.com.abhilash.splitwise.entity.User;
import strategy.EqualExpenseSplitter;
import strategy.ExactExpenseSplitter;

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
            DebtSimplificationService debtSimplificationService = new DebtSimplificationService(balanceRepository);

            User abhilash = new User("1","Abhilash","a@gmail.com");
            User rahul = new User("2","Rahul","r@gmail.com");
            User abhishek = new User("3","Abhishek","ab@gmail.com");
            User mirang = new User("4","Mirang","m@gmail.com");

            userService.registerUser(abhilash);
            userService.registerUser(rahul);
            userService.registerUser(abhishek);
            userService.registerUser(mirang);

            Set<User> members = new HashSet<>();

            members.add(abhilash);
            members.add(rahul);
            members.add(abhishek);
            members.add(mirang);

            Group germanyTrip = groupService.createGroup("Spain Trip", members);

            // Hotel Expense
            expenseService.createGroupExpense("EXP-1", 1200, abhilash, List.of(new Split(abhilash,0), new Split(rahul,0), new Split(abhishek,0), new Split(mirang,0)), new EqualExpenseSplitter(), germanyTrip);

            // Dinner Expense
            expenseService.createGroupExpense("EXP-2", 800, rahul, List.of(new Split(abhilash,0), new Split(rahul,0), new Split(abhishek,0), new Split(mirang,0)), new EqualExpenseSplitter(), germanyTrip);

            // Museum Expense
            expenseService.createGroupExpense("EXP-3", 400, abhishek, List.of(new Split(abhilash,200), new Split(abhishek,200)), new ExactExpenseSplitter(), germanyTrip);

            System.out.println("===== CURRENT BALANCES =====");

            balanceService.showAllBalances();

            System.out.println();
            System.out.println("===== SETTLEMENT FLOW=====");
            System.out.println("Rahul paid 100 to Abhilash");

            balanceService.settleBalance(rahul, abhilash,100);
            System.out.println();
            System.out.println("===== FINAL BALANCES AFTER NETTING =====");
            balanceService.showAllBalances();

            System.out.println();
            System.out.println("===== SIMPLIFIED DEBTS =====");

            List<Settlement> settlements = debtSimplificationService.getSimplifiedBalance();

            for(Settlement settlement : settlements){
                System.out.println(userRepository.findById(settlement.getDebtor()).getName() + " -> " + userRepository.findById(settlement.getCreditor()).getName() + " = ₹" + settlement.getAmount());
            }
    }
}