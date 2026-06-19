package service;

import exceptions.InvalidGroupException;
import repository.BalanceRepository;
import repository.ExpenseRepository;
import strategy.ExpenseSplitter;
import entities.Expense;
import entities.Group;
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
        createExpenseInternal(expenseId,amount,paidBy,splitList,null);
    }

    public void createGroupExpense(String expenseId, double amount, User paidBy, List<Split> splits, ExpenseSplitter splitter, Group group){
        validateGroupExpense(group,paidBy,splits);
        List<Split> splitList = splitter.split(amount,splits);
        createExpenseInternal(expenseId,amount,paidBy,splitList,group);
    }

    private void createExpenseInternal(String expenseId, double amount, User paidBy, List<Split> splitList, Group group){
        Expense expense = new Expense(expenseId,amount,paidBy,splitList,group);
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

    private void validateGroupExpense(Group group, User paidBy,List<Split> splits){
        if(group == null){
            throw new InvalidGroupException("Group can't be null");
        }

        if(!group.getMembers().contains(paidBy)){
            throw new InvalidGroupException("Payer is not the part of the group");
        }

        // For cases where the list of the split doesn't contain the members of the group
        for(Split split : splits){
            if(!group.getMembers().contains(split.getUser())){
                throw new InvalidGroupException("Participants are not the part of the group");
            }
        }

    }
}
