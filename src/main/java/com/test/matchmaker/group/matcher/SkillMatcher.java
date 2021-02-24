package com.test.matchmaker.group.matcher;

import com.test.matchmaker.user.User;

/**
 * Матчер для проверки Skill игрока.
 */
public class SkillMatcher extends BaseMatcher {

    private final double baseSkill;

    public SkillMatcher(double minDiffSkill, double baseSkill, double incrementPerSec) {
        super(minDiffSkill, incrementPerSec);
        this.baseSkill = baseSkill;
    }

    @Override
    public boolean isMatch(User user, long waitPeriod) {
        return isMatch(baseSkill, user.getSkill(), waitPeriod);
    }
}
