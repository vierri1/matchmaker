package com.test.matchmaker.group;

import com.test.matchmaker.group.matcher.IMatcher;
import com.test.matchmaker.group.statistics.StatisticsCalculator;
import com.test.matchmaker.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class GroupTest {

    @Mock
    private IMatcher matcher;
    @Mock
    private User user;
    @Mock
    private User user2;
    @Mock
    private User user3;
    private Group group;

    @BeforeEach
    void beforeEach() {
        group = new Group("id",
                Collections.singleton(matcher),
                2,
                10,
                new StatisticsCalculator());
    }

    @Test
    void addUser_userMatchesTest() {
        Mockito.when(matcher.isMatch(any(), anyLong())).thenReturn(true);

        assertTrue(group.add(user), "Игрок должен быть добавлен");
    }

    @Test
    void addUser_userNotMatchesTest() {
        Mockito.when(matcher.isMatch(any(), anyLong())).thenReturn(false);

        assertFalse(group.add(user), "Игрок не должен быть добавлен");
    }

    @Test
    void addUser_groupIsFullTest() {
        Mockito.when(matcher.isMatch(any(), anyLong())).thenReturn(true);
        group.add(user);
        group.add(user2);

        assertFalse(group.add(user3), "Группа уже полная");
    }

    @Test
    void isFullTest() {
        Mockito.when(matcher.isMatch(any(), anyLong())).thenReturn(true);

        group.add(user);
        assertFalse(group.isFull(), "Группа еще не полная");

        group.add(user2);
        assertTrue(group.isFull(), "Группа дложна быть полной");
    }

}