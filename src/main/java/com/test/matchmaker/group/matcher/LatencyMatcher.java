package com.test.matchmaker.group.matcher;

import com.test.matchmaker.user.User;

public class LatencyMatcher extends BaseMatcher {

    private final double baseLatency;

    public LatencyMatcher(double minDiffLatency, double baseLatency, double incrementPerSec) {
        super(minDiffLatency, incrementPerSec);
        this.baseLatency = baseLatency;
    }

    @Override
    public boolean isMatch(User user, long waitPeriod) {
        return isMatch(baseLatency, user.getLatency(), waitPeriod);
    }
}
