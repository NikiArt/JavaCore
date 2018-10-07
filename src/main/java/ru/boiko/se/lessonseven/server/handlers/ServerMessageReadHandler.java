package ru.boiko.se.lessonseven.server.handlers;

import com.sun.istack.internal.NotNull;
import ru.boiko.se.lessonseven.server.ConnectionService;
import ru.boiko.se.lessonseven.server.events.ServerMessageInputEvent;
import ru.boiko.se.lessonseven.server.events.ServerMessageReadEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.io.DataInputStream;
import java.io.InputStream;

@ApplicationScoped
public class ServerMessageReadHandler {

    @Inject
    private ConnectionService connectionService;

    @Inject
    private Event<ServerMessageReadEvent> serverMessageReadEventEvent;

    @Inject
    private Event<ServerMessageInputEvent> serverMessageInputEventEvent;

    public void handler(@ObservesAsync final ServerMessageReadEvent event) {
        System.out.println("ServerMessageReadEvent");
        try {
            @NotNull final InputStream inputStream = event.getSocket().getInputStream();
            @NotNull final DataInputStream in = new DataInputStream(inputStream);
            @NotNull final String message = in.readUTF();
            serverMessageReadEventEvent.fireAsync(new ServerMessageReadEvent(event.getSocket(), ""));
            serverMessageInputEventEvent.fireAsync(new ServerMessageInputEvent(event.getSocket(), message));
        } catch (final Exception e) {
            connectionService.remove(event.getSocket());
        }
    }
}
