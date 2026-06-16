package Service;

import Repository.BalanceRepository;
import Repository.ExpenseRepository;
import Strategy.ExpenseSplitter;
import entities.Expense;
import entities.Split;
import entities.User;

import java.util.List;

public class ExpenseService {
    public ExpenseRepository expenseRepository;
    public BalanceRepository balanceRepository;
    public ExpenseService(ExpenseRepository expenseRepository,BalanceRepository balanceRepository){
        this.expenseRepository = expenseRepository;
        this.balanceRepository = balanceRepository;
    }
    public void createExpense(String expenseId, double amount, User paidBy, List<Split> splits, ExpenseSplitter splitter){
        List<Split> splitList = splitter.split(amount,splits);
        Expense expense = new Expense(expenseId,amount,paidBy,splitList);
        expenseRepository.save(expense);
        updateBalances(paidBy,splitList);
    }
    private void updateBalances(User paidBy,List<Split> splitList){
        for (Split split : splitList) {
            User participant = split.getUser();
            if (participant.getId().equals(paidBy.getId())) {
                continue;
            }
            balanceRepository.updateBalance(paidBy, participant, (long) split.getAmount());
        }
    }
}
