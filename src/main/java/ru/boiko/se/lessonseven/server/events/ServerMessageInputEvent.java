package ru.boiko.se.lessonseven.server.events;

import com.sun.istack.internal.NotNull;
import ru.boiko.se.lessonseven.server.ServerMessageEvent;

import java.net.Socket;

public final class ServerMessageInputEvent extends ServerMessageEvent {
    public ServerMessageInputEvent(@NotNull Socket socket,@NotNull String message) {
        super(socket, message);
    }
}
