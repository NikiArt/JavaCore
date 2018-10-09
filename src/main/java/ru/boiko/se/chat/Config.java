package ru.boiko.se.chat;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Config {
    private final Integer socket;
    private final String host;

    public Config() {
        socket = 8179;
        host = "localhost";
    }
}
