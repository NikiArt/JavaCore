package ru.boiko.se.lessonseven.server.events;

import ru.boiko.se.lessonseven.server.ServerMessageEvent;

import java.net.Socket;

public final class ServerRegistryEvent extends ServerMessageEvent {
    public ServerRegistryEvent(Socket socket, String message) {
        super(socket, message);
    }
}
