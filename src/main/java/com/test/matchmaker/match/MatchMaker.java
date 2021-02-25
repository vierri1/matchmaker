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

    /**
     * Добавляет игроков в очередь на создание матча.
     */
    public synchronized void add(Set<User> users) {
        removeDuplicates(users);
        userPool.addAll(users);
        make();
    }

    /**
     * Строит группы игроков, находящихся в очереди.
     */
    public synchronized void make() {
        Set<Group> readyGroups = groupMaker.create(userPool);
        readyGroups.forEach(this::startMatch);
    }

    private void startMatch(Group group) {
        userPool.removeAll(group.getUsers());
        System.out.println("Match started");
        System.out.println(group);
    }

    /**
     * Одни и те же игроки могут приходить с разными характеристиками.
     * В очереди необходимо оставить более свежие данные.
     */
    private void removeDuplicates(Set<User> users) {
        userPool.removeIf(users::contains);
    }
}
