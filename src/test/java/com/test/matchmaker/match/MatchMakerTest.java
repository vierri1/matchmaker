package com.test.matchmaker.match;

import com.test.matchmaker.group.Group;
import com.test.matchmaker.group.GroupMaker;
import com.test.matchmaker.group.statistics.StatisticsCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MatchMakerTest {

    @Mock
    private GroupMaker groupMaker;
    private Set<Group> groups;
    @Spy
    private Group group =
            new Group("id", Collections.emptySet(), 2, 10, new StatisticsCalculator());
    private MatchMaker matchMaker;

    @BeforeEach
    void beforeEach() {
        groups = new HashSet<>();
        groups.add(group);
        Mockito.when(groupMaker.create(Mockito.anySet())).thenReturn(groups);
        matchMaker = new MatchMaker(groupMaker);
    }

    @Test
    void addUsersTest() {
        matchMaker.add(Collections.emptySet());

        Mockito.verify(groupMaker, Mockito.times(1)).create(Mockito.anySet());
        Mockito.verify(group, Mockito.times(2)).getUsers();
    }

    @Test
    void makeTest() {
        matchMaker.make();

        Mockito.verify(groupMaker, Mockito.times(1)).create(Mockito.anySet());
        Mockito.verify(group, Mockito.times(2)).getUsers();
    }

}