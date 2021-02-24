package com.test.matchmaker.user;

import com.test.matchmaker.dto.UserDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class User implements Comparable<User> {
    @EqualsAndHashCode.Include
    private final String name;
    private final double skill;
    private final double latency;
    private final LocalDateTime startDate;

    private Comparator<User> comparator = Comparator.comparing(User::getStartDate).thenComparing(User::getName);

    public static User of(UserDto dto) {
        return new User(dto.getName(), dto.getSkill(), dto.getLatency(), LocalDateTime.now());
    }

    public long getWaitPeriod() {
        Duration period = Duration.between(startDate, LocalDateTime.now());
        return period.getSeconds();
    }

    @Override
    public int compareTo(User user) {
        return comparator.compare(this, user);
    }
}
