package strategy;

import src.main.java.com.abhilash.splitwise.entity.Split;

import java.util.List;

public class EqualExpenseSplitter implements ExpenseSplitter {
    @Override
    public List<Split> split(double amount, List<Split> splits) {
        double share = amount / splits.size();
        for(Split split : splits){
            split.setAmount(share);
        }
        return splits;
    }
}