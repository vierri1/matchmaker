package com.test.matchmaker.group.matcher;

import com.test.matchmaker.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MatcherFactory {
    private final Double minDiffSkill;
    private final Double incrementPerSecSkill;
    private final Double minDiffLatency;
    private final Double incrementPerSecLatency;

    public MatcherFactory(@Value("${min.diff.skill:5}") Double minDiffSkill,
                          @Value("${increment.per.sec.skill:1}") Double incrementPerSecSkill,
                          @Value("${min.diff.latency:5}") Double minDiffLatency,
                          @Value("${increment.per.sec.latency:1}") Double incrementPerSecLatency) {
        this.minDiffSkill = minDiffSkill;
        this.incrementPerSecSkill = incrementPerSecSkill;
        this.minDiffLatency = minDiffLatency;
        this.incrementPerSecLatency = incrementPerSecLatency;
    }

    public Set<IMatcher> create(User user) {
        Set<IMatcher> matchers = new HashSet<>();
        matchers.add(new SkillMatcher(minDiffSkill, user.getSkill(), incrementPerSecSkill));
        matchers.add(new LatencyMatcher(minDiffLatency, user.getLatency(), incrementPerSecLatency));
        return matchers;
    }
}
