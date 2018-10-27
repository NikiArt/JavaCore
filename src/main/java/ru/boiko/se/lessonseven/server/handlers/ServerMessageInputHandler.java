package ru.boiko.se.lessonseven.server.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.NotNull;
import lombok.SneakyThrows;
import ru.boiko.se.lessonseven.server.events.*;
import ru.boiko.se.lessonseven.server.model.Packet;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.net.Socket;

@ApplicationScoped
public class ServerMessageInputHandler {

    @Inject
    private Event<ServerPingEvent> serverPingEvent;

    @Inject
    private Event<ServerRegistryEvent> serverRegistryEvent;

    @Inject
    private Event<ServerLoginEvent> serverLoginEvent;

    @Inject
    private Event<ServerBroadcastEvent> serverBroadcastEvent;

    @Inject
    private Event<ServerLogoutEvent> serverLogoutEvent;

    @SneakyThrows
    public void observe(@ObservesAsync final ServerMessageInputEvent event) {
        System.out.println("ServerMessageInputEvent");
        @NotNull final Socket socket = event.getSocket();
        @NotNull final String message = event.getMessage();
        System.out.println(message);
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final Packet packet = objectMapper.readValue(message, Packet.class);

        switch (packet.getType()) {
            case PING:
                serverPingEvent.fireAsync(new ServerPingEvent(socket, message));
                break;

            case REGISTRY:
                serverRegistryEvent.fireAsync(new ServerRegistryEvent(socket, message));
                break;

            case LOGIN:
                serverLoginEvent.fireAsync(new ServerLoginEvent(socket, message));
                break;

            case BROADCAST:
                serverBroadcastEvent.fireAsync(new ServerBroadcastEvent(socket, message));
                break;

            case LOGOUT:
                serverLogoutEvent.fireAsync(new ServerLogoutEvent(socket, message));


        }
    }
}
