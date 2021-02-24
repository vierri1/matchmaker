package com.test.matchmaker.group.matcher;


import com.test.matchmaker.user.User;

public interface IMatcher {
    boolean isMatch(User user, long waitPeriod);
}
