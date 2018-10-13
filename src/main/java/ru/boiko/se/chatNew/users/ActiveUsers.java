package ru.boiko.se.chatNew.users;

import lombok.Getter;
import lombok.Setter;
import ru.boiko.se.chat.server.Connection;

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
