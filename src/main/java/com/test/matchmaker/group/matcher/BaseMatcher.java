package com.test.matchmaker.group.matcher;


public abstract class BaseMatcher implements IMatcher {

    private final double incrementPerSec;
    private final double minCoefficient;

    public BaseMatcher(double minCoefficient, double incrementPerSec) {
        this.minCoefficient = minCoefficient / 100;
        this.incrementPerSec = incrementPerSec / 100;
    }

    protected boolean isMatch(Double baseValue, Double value, long waitPeriod) {
        double diff = baseValue * (minCoefficient + computeDiffCoefficient(waitPeriod));
        return isInRange(baseValue, value, diff);
    }

    private double computeDiffCoefficient(long waitPeriod) {
        return incrementPerSec * waitPeriod;
    }

    private boolean isInRange(Double baseValue, Double value, Double diff){
        return (baseValue + diff) >= value && (baseValue - diff) <= value;
    }
}
