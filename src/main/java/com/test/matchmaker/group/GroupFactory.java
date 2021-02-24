package com.test.matchmaker.group;

import com.test.matchmaker.group.matcher.LatencyMatcher;
import com.test.matchmaker.group.statistics.StatisticsCalculator;
import com.test.matchmaker.group.matcher.IMatcher;
import com.test.matchmaker.group.matcher.SkillMatcher;
import com.test.matchmaker.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class GroupFactory {

    private final Integer groupSize;
    private final StatisticsCalculator statisticsCalculator;
    private final Double minDiffSkill;
    private final Double incrementPerSecSkill;
    private final Double minDiffLatency;
    private final Double incrementPerSecLatency;

    @Autowired
    public GroupFactory(@Value("${group.size:2}") Integer groupSize,
                        @Value("${min.diff.skill:5}") Double minDiffSkill,
                        @Value("${min.diff.latency:5}") Double minDiffLatency,
                        @Value("${increment.per.sec.skill:1}") Double incrementPerSecSkill,
                        @Value("${increment.per.sec.latency:1}") Double incrementPerSecLatency,
                        StatisticsCalculator statisticsCalculator) {
        this.groupSize = groupSize;
        this.minDiffSkill = minDiffSkill;
        this.minDiffLatency = minDiffLatency;
        this.incrementPerSecSkill = incrementPerSecSkill;
        this.incrementPerSecLatency = incrementPerSecLatency;
        this.statisticsCalculator = statisticsCalculator;
    }

    public Group create(User user) {
        Group group = new Group(
                UUID.randomUUID().toString(),
                createMatchers(user),
                groupSize,
                user.getWaitPeriod(),
                statisticsCalculator);
        group.add(user);
        return group;
    }

    private Set<IMatcher> createMatchers(User user) {
        Set<IMatcher> matchers = new HashSet<>();
        matchers.add(new SkillMatcher(minDiffSkill, user.getSkill(), incrementPerSecSkill));
        matchers.add(new LatencyMatcher(minDiffLatency, user.getLatency(), incrementPerSecLatency));
        return matchers;
    }
}
