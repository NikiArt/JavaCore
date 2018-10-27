package ru.boiko.se.lessonseven.server.events;

import ru.boiko.se.lessonseven.server.ServerMessageEvent;

import java.net.Socket;

public final class ServerLoginEvent extends ServerMessageEvent {
    public ServerLoginEvent(Socket socket, String message) {
        super(socket, message);
    }
}
