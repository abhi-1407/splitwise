package Strategy;

import entities.Split;
import entities.User;

import java.util.List;

public class ExactExpenseSplitter implements ExpenseSplitter{
    @Override
    public List<Split> split(double amount, List<Split> splits) {
        double total = 0;
        for(Split split : splits){
            total += split.getAmount();
        }
        if(total != amount){
            throw new RuntimeException("Invalid Exact Split");
        }
        return splits;
    }
}
