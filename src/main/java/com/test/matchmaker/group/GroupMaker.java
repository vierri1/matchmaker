package com.test.matchmaker.group;

import com.test.matchmaker.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class GroupMaker {

    private final GroupFactory groupFactory;

    public Set<Group> create(Set<User> users) {
        TreeSet<User> sortedUsers = new TreeSet<>(users);
        Set<Group> groups = new HashSet<>();

        sortedUsers.iterator().forEachRemaining(user ->{
            boolean added = addToSuitableGroup(user, groups);
            if (!added) {
                groups.add(groupFactory.create(user));
            }
        });

        return groups.stream()
                .filter(Group::isFull)
                .collect(Collectors.toSet());
    }

    private boolean addToSuitableGroup(User user, Set<Group> groups) {
        return groups.stream()
                .anyMatch(group -> group.add(user));
    }
}
