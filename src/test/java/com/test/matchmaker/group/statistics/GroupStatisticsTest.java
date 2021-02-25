package com.test.matchmaker.group.statistics;

import com.test.matchmaker.user.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GroupStatisticsTest {

    @Test
    public void statisticsTest() {
        GroupStatistics statistics = new GroupStatistics();
        User user1 = new User("Killer", 10, 100, LocalDateTime.now());
        User user2 = new User("Killer2", 20, 200, LocalDateTime.now());

        statistics.addFor(user1);
        statistics.addFor(user2);

        assertEquals(15, statistics.getAvgSkill());
        assertEquals(150, statistics.getAvgLatency());
        assertEquals(10, statistics.getMinSkill());
        assertEquals(20, statistics.getMaxSkill());
        assertEquals(100, statistics.getMinLatency());
        assertEquals(200, statistics.getMaxLatency());
    }

}