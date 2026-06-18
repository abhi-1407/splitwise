package strategy;

import entities.PercentageSplit;
import entities.Split;
import exceptions.InvalidPercentageException;

import java.util.List;

public class PercentageExpenseSplit implements ExpenseSplitter {

    @Override
    public List<Split> split(double amount, List<Split> splits) {
        double totalPercentage = 0;
        for(Split split : splits){
            PercentageSplit percentageSplit = (PercentageSplit) split;
            totalPercentage += percentageSplit.getPercentage();
        }
        if(totalPercentage != 100){
            throw new InvalidPercentageException();
        }

        for(Split split : splits){
            PercentageSplit percentageSplit = (PercentageSplit)split;
            double splitAmount = (amount * percentageSplit.getPercentage()) / 100;
            split.setAmount(splitAmount);
        }
        return splits;
    }
}
