package com.test.matchmaker.group;

import com.test.matchmaker.group.matcher.IMatcher;
import com.test.matchmaker.group.matcher.MatcherFactory;
import com.test.matchmaker.group.statistics.StatisticsCalculator;
import com.test.matchmaker.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class GroupMakerTest {

    private GroupFactory groupFactory;
    @Mock
    private MatcherFactory matcherFactory;
    @Mock
    private IMatcher matcher;
    private GroupMaker groupMaker;
    private User user1;
    private User user2;

    @BeforeEach
    void beforeEach() {
        Mockito.when(matcherFactory.create(any())).thenReturn(Collections.singleton(matcher));
        groupFactory = new GroupFactory(1, matcherFactory, new StatisticsCalculator());
        groupMaker = new GroupMaker(groupFactory);

        user1 = new User("Killer1", 10, 15, LocalDateTime.now());
        user2 = new User("Killer2", 10, 15, LocalDateTime.now());
    }

    @Test
    void createGroups_allUserMatchesTest() {
        Mockito.when(matcher.isMatch(any(), anyLong())).thenReturn(true);
        Set<User> users = new HashSet<>();
        users.add(user1);
        users.add(user2);

        Set<Group> groups = groupMaker.create(users);

        assertEquals(2, groups.size(), "Должно быть 2 группы");
    }

    @Test
    void createGroups_oneUserMatchesTest() {
        Mockito.when(matcher.isMatch(any(), anyLong())).thenReturn(true).thenReturn(false);
        Set<User> users = new HashSet<>();
        users.add(user1);
        users.add(user2);

        Set<Group> groups = groupMaker.create(users);

        assertEquals(1, groups.size(), "Должна быть 1 группа");
    }

    @Test
    void createGroups_allUsersNotMatchesTest() {
        Mockito.when(matcher.isMatch(any(), anyLong())).thenReturn(false);
        Set<User> users = new HashSet<>();
        users.add(user1);
        users.add(user2);

        Set<Group> groups = groupMaker.create(users);

        assertTrue(groups.isEmpty(), "Не должно быть групп");
    }
}