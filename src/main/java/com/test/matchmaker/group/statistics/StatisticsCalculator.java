package com.test.matchmaker.group.statistics;


import com.test.matchmaker.group.Group;
import org.springframework.stereotype.Component;

@Component
public class StatisticsCalculator {

    public GroupStatistics statisticsFor(Group group) {
        GroupStatistics stats = new GroupStatistics();
        group.getUsers().forEach(stats::addFor);
        return stats;
    }

}
