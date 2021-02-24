package com.test.matchmaker.match;

import com.test.matchmaker.group.Group;
import com.test.matchmaker.group.GroupMaker;
import com.test.matchmaker.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class MatchMaker {

    private final GroupMaker groupMaker;
    private final Set<User> userPool = new HashSet<>();

    public synchronized void add(Set<User> users) {
        userPool.removeAll(users);
        userPool.addAll(users);
        make();
    }

    public synchronized void make() {
        Set<Group> readyGroups = groupMaker.create(userPool);
        readyGroups.forEach(this::startMatch);
    }

    private void startMatch(Group group) {
        userPool.removeAll(group.getUsers());
        System.out.println("Match started");
        System.out.println(group);
    }
}
