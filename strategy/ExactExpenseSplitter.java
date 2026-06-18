package strategy;

import entities.Split;
import exceptions.InvalidSplitException;

import java.util.List;

public class ExactExpenseSplitter implements ExpenseSplitter{
    @Override
    public List<Split> split(double amount, List<Split> splits) {
        double total = 0;
        for(Split split : splits){
            total += split.getAmount();
        }
        if(total != amount){
            throw new InvalidSplitException("Invalid Split total is not equal to amount");
        }
        return splits;
    }
}
