package ru.boiko.se.lessonseven.server.events;

import ru.boiko.se.lessonseven.server.ServerMessageEvent;

import java.net.Socket;

public final class ServerPingEvent extends ServerMessageEvent {
    public ServerPingEvent(Socket socket, String message) {
        super(socket, message);
    }
}
