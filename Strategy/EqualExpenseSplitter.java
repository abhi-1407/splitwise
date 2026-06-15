package Strategy;

import Strategy.ExpenseSplitter;
import entities.Split;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class EqualExpenseSplitter implements ExpenseSplitter {

    @Override
    public List<Split> split(double amount, List<User> users) {

        List<Split> splits = new ArrayList<>();

        double share = amount / users.size();

        for (User user : users) {
            splits.add(new Split(user, share));
        }

        return splits;
    }
}