package ru.boiko.se.lessonseven.server.events;

import com.sun.istack.internal.NotNull;
import ru.boiko.se.lessonseven.server.ServerMessageEvent;

import java.net.Socket;

public final class ServerMessageReadEvent extends ServerMessageEvent {
    public ServerMessageReadEvent(@NotNull Socket socket, String message) {
        super(socket, message);
    }
}
