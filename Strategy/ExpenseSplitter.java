package Strategy;

import entities.Split;
import entities.User;

import java.util.List;

public interface ExpenseSplitter {

    List<Split> split(double amount, List<Split> splits);
}
