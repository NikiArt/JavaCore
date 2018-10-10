package ru.boiko.se.chat.server;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ActiveUsers {
    private static ActiveUsers instance;
    private final List<Connection> activeUsers = new ArrayList<>();

    private ActiveUsers() {
    }

    public static synchronized ActiveUsers getInstance() {
        if (instance == null) {
            instance = new ActiveUsers();
        }
        return instance;
    }
}
