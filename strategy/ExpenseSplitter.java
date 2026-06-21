package strategy;

import src.main.java.com.abhilash.splitwise.entity.Split;

import java.util.List;

public interface ExpenseSplitter {

    List<Split> split(double amount, List<Split> splits);
}
