package com.test.matchmaker.group.matcher;

import com.test.matchmaker.user.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SkillMatcherTest {

    @Test
    void userMatchTest() {
        SkillMatcher skillMatcher = new SkillMatcher(0, 100, 0);
        User user = new User("Killer", 100, 15, LocalDateTime.now());

        assertTrue(skillMatcher.isMatch(user, 0), "Игрок должен подходить");
    }

    @Test
    void userNotMatchTest() {
        SkillMatcher skillMatcher = new SkillMatcher(0, 101, 0);
        User user = new User("Killer", 100, 15, LocalDateTime.now());

        assertFalse(skillMatcher.isMatch(user, 0), "Игрок не должен подходить");
    }

    @Test
    void userMatchWithDiffTest() {
        SkillMatcher skillMatcher = new SkillMatcher(5, 100, 0);
        User user = new User("Killer", 105, 15, LocalDateTime.now());

        assertTrue(skillMatcher.isMatch(user, 0), "Игрок должен подходить");
    }

    @Test
    void userMatchWithDiffPeriodTest() {
        SkillMatcher skillMatcher = new SkillMatcher(0, 100, 1);
        User user = new User("Killer", 105, 15, LocalDateTime.now());

        assertFalse(skillMatcher.isMatch(user, 0), "Игрок не должен подходить");
        assertTrue(skillMatcher.isMatch(user, 5), "Игрок должен подходить");
    }

}