package ru.boiko.se.lessonsix;

import lombok.Getter;
import lombok.Setter;

import java.net.Socket;

@Getter
@Setter
public class Config {
    private final Integer socket;
    private final String host;

    public Config(){
        socket = 8179;
        host = "localhost";
    }
}
