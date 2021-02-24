package com.test.matchmaker.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
public class UsersDto {
    @Getter
    @Setter
    private List<UserDto> users;
}
