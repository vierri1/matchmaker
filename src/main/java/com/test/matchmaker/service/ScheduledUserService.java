package com.test.matchmaker.service;

import com.test.matchmaker.match.MatchMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledUserService {

    private final MatchMaker matchMaker;

    @Autowired
    public ScheduledUserService(MatchMaker matchMaker) {
        this.matchMaker = matchMaker;
    }

    @Scheduled(fixedDelayString = "${make.match.interval.millis:1000}")
    public void makeMatch() {
        matchMaker.make();
    }
}
