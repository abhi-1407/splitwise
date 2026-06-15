import Strategy.EqualExpenseSplitter;
import Strategy.ExpenseSplitter;
import entities.Split;
import entities.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        User user1 = new User("1", "A", "a@gmail.com");
        User user2 = new User("2", "B", "b@gmail.com");
        User user3 = new User("3", "C", "c@gmail.com");

        List<User> users = List.of(user1, user2, user3);

        ExpenseSplitter splitter = new EqualExpenseSplitter();

        List<Split> splits = splitter.split(900, users);

        for (Split split : splits) {
            System.out.println(
                    split.getUser().getName()
                            + " -> "
                            + split.getAmount()
            );
        }
    }
}