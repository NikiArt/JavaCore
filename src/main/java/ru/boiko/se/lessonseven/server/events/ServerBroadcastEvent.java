package ru.boiko.se.lessonseven.server.events;

import ru.boiko.se.lessonseven.server.ServerMessageEvent;

import java.net.Socket;

public final class ServerBroadcastEvent extends ServerMessageEvent {
    public ServerBroadcastEvent(Socket socket, String message) {
        super(socket, message);
    }
}
