package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum TypeOfPerson {
    PRODUCER("Режессер"),
    ACTOR("Актер"),
    AUTHOR("Автор");

    private final String name;
}
