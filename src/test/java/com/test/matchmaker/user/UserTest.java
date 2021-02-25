package com.test.matchmaker.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void sortByStartDateTest() {
        LocalDateTime now = LocalDateTime.now();
        User user1 = new User("Killer1", 10, 15, now);
        User user2 = new User("Killer2", 10, 15, now.minusMinutes(5));

        assertTrue(user1.compareTo(user2) > 0, "Killer2 должен быть меньше");
    }

    @Test
    void sortByNameTest() {
        LocalDateTime now = LocalDateTime.now();
        User user1 = new User("Killer1", 10, 15, now);
        User user2 = new User("Killer2", 10, 15, now);

        assertTrue(user1.compareTo(user2) < 0, "Killer1 должен быть меньше");
    }

}