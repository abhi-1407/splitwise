package com.abhilash.splitwise.entity;

public class PercentageSplit extends Split{
    private double percentage;
    public PercentageSplit(User user, double share, double percentage) {
        super(user, share);
        this.percentage = percentage;
    }

    public double getPercentage(){
        return percentage;
    }
}
