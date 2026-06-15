package entities;

import java.util.List;

public class Expense {
    private String expenseId;
    private String description;
    private double amount;
    private User paidBy;
    private List<User> participants;
    private List<Split> splits;
}
