package strategy;

import entities.Split;

import java.util.List;

public interface ExpenseSplitter {

    List<Split> split(double amount, List<Split> splits);
}
