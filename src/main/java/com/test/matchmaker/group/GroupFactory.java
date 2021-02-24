package com.test.matchmaker.group;

import com.test.matchmaker.group.matcher.LatencyMatcher;
import com.test.matchmaker.group.matcher.MatcherFactory;
import com.test.matchmaker.group.statistics.StatisticsCalculator;
import com.test.matchmaker.group.matcher.IMatcher;
import com.test.matchmaker.group.matcher.SkillMatcher;
import com.test.matchmaker.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GroupFactory {

    private final Integer groupSize;
    private final StatisticsCalculator statisticsCalculator;
    private final MatcherFactory matcherFactory;


    @Autowired
    public GroupFactory(@Value("${group.size:2}") Integer groupSize,
                        MatcherFactory matcherFactory,
                        StatisticsCalculator statisticsCalculator) {
        this.groupSize = groupSize;
        this.statisticsCalculator = statisticsCalculator;
        this.matcherFactory = matcherFactory;
    }

    public Group create(User user) {
        Group group = new Group(
                UUID.randomUUID().toString(),
                matcherFactory.create(user),
                groupSize,
                user.getWaitPeriod(),
                statisticsCalculator);
        group.add(user);
        return group;
    }
}
