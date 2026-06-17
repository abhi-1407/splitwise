import Repository.BalanceRepository;
import Repository.ExpenseRepository;
import Repository.InMemoryBalanceRepository;
import Repository.InMemoryExpenseRepository;
import Service.ExpenseService;
import Strategy.EqualExpenseSplitter;
import Strategy.ExpenseSplitter;
import entities.Split;
import entities.User;

import java.util.List;

public class Splitwise {
    public static void main(String[] args) {
        User user1 = new User("1", "Abhilash", "a@gmail.com");
        User user2 = new User("2", "Rahul", "r@gmail.com");

        List<Split> splitList = List.of(
                new Split(user1,0),
                new Split(user2,0)
        );

        ExpenseRepository expenseRepository = new InMemoryExpenseRepository();
        BalanceRepository balanceRepository = new InMemoryBalanceRepository();

        ExpenseService expenseService = new ExpenseService(expenseRepository, balanceRepository);

        expenseService.createExpense("EXP-1",900,user1,splitList,new EqualExpenseSplitter());

        System.out.println("Rahul owes Abhilash = "+ balanceRepository.getBalance(user1,user2));
        System.out.println("Abhilash owes Rahul = "+ balanceRepository.getBalance(user2,user1));
        System.out.println("-----------");

        expenseService.createExpense("EXP-2",900,user2,splitList,new EqualExpenseSplitter());
        System.out.println("Rahul owes Abhilash = "+ balanceRepository.getBalance(user1,user2));
        System.out.println("Abhilash owes Rahul = "+ balanceRepository.getBalance(user2,user1));
    }
}