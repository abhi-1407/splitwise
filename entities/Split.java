package entities;

public class Split {
    private final User user;
    private final double amount;

    public Split(User user, double share) {
        this.user = user;
        this.amount = share;
    }

    public double getAmount() {
        return amount;
    }

    public User getUser() {
        return user;
    }
}
