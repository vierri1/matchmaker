package com.test.matchmaker.group;

import com.test.matchmaker.group.statistics.GroupStatistics;
import com.test.matchmaker.group.statistics.StatisticsCalculator;
import com.test.matchmaker.group.matcher.IMatcher;
import com.test.matchmaker.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.TreeSet;

@RequiredArgsConstructor
public class Group {

    private final String id;
    private final Set<IMatcher> matchers;
    private final int groupSize;
    private final long waitPeriod;
    private final StatisticsCalculator statisticsCalculator;
    @Getter
    private Set<User> users = new TreeSet<>();

    @Override
    public String toString() {
        GroupStatistics stats = statisticsCalculator.statisticsFor(this);
        StringBuilder sb = new StringBuilder();
        sb.append("Group: ").append(id).append("\n");
        sb.append("Statistics: ").append("\n");
        sb.append(stats);
        sb.append("Players:\n");
        users.forEach(user -> sb.append(user.getName()).append("\n"));
        return sb.toString();
    }

    boolean add(User user) {
        if (!isFull() && isMatch(user)) {
            users.add(user);
            return true;
        }
        return false;
    }

    boolean isFull() {
        return size() == groupSize;
    }

    private int size() {
        return users.size();
    }

    private boolean isMatch(User user) {
        return matchers.stream()
                .allMatch(matcher -> matcher.isMatch(user, waitPeriod));
    }
}
