package entities;

import java.util.List;

public class Expense {

    private final String expenseId;
    private final double amount;
    private final User paidBy;
    private final List<Split> splits;
    private Group group;

    public Expense(String expenseId,double amount,User paidBy,List<Split> splits,Group group){
        this.expenseId = expenseId;
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.group = group;
    }

    public String getExpenseId(){
        return expenseId;
    }

    public Group getGroup(){
        return group;
    }

}
