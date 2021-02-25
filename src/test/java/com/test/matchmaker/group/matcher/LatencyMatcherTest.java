package com.test.matchmaker.group.matcher;

import com.test.matchmaker.user.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LatencyMatcherTest {
    @Test
    void userMatchTest() {
        LatencyMatcher skillMatcher = new LatencyMatcher(0, 100, 0);
        User user = new User("Killer", 10, 100, LocalDateTime.now());

        assertTrue(skillMatcher.isMatch(user, 0), "Игрок должен подходить");
    }

    @Test
    void userNotMatchTest() {
        LatencyMatcher skillMatcher = new LatencyMatcher(0, 101, 0);
        User user = new User("Killer", 10, 100, LocalDateTime.now());

        assertFalse(skillMatcher.isMatch(user, 0), "Игрок не должен подходить");
    }

    @Test
    void userMatchWithDiffTest() {
        LatencyMatcher skillMatcher = new LatencyMatcher(5, 100, 0);
        User user = new User("Killer", 10, 105, LocalDateTime.now());

        assertTrue(skillMatcher.isMatch(user, 0), "Игрок должен подходить");
    }

    @Test
    void userMatchWithDiffPeriodTest() {
        LatencyMatcher skillMatcher = new LatencyMatcher(0, 100, 1);
        User user = new User("Killer", 10, 105, LocalDateTime.now());

        assertFalse(skillMatcher.isMatch(user, 0), "Игрок не должен подходить");
        assertTrue(skillMatcher.isMatch(user, 5), "Игрок должен подходить");
    }


}