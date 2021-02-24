package com.test.matchmaker.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String name;
    private double skill;
    private double latency;
}
