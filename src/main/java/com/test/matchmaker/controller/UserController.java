package com.test.matchmaker.controller;

import com.test.matchmaker.dto.ResponseDto;
import com.test.matchmaker.dto.UsersDto;
import com.test.matchmaker.match.MatchMaker;
import com.test.matchmaker.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private MatchMaker matchMaker;

    @Autowired
    public UserController(MatchMaker matchMaker) {
        this.matchMaker = matchMaker;
    }

    @PostMapping("/users")
    public ResponseDto add(@RequestBody UsersDto userDtos) {
        Set<User> users = userDtos.getUsers()
                .stream()
                .map(User::of)
                .collect(Collectors.toSet());
        matchMaker.add(users);
        return new ResponseDto(HttpStatus.OK.value());
    }

}
