package ru.boiko.se.lessonseven.server.handlers;


import lombok.SneakyThrows;
import ru.boiko.se.lessonseven.server.ConnectionService;
import ru.boiko.se.lessonseven.server.Server;
import ru.boiko.se.lessonseven.server.ServerMessageEvent;
import ru.boiko.se.lessonseven.server.events.ServerConnectionEvent;
import ru.boiko.se.lessonseven.server.events.ServerMessageReadEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.net.Socket;

@ApplicationScoped
public class ServerConnectionHandler {

    @Inject
    private Server server;

    @Inject
    private ConnectionService connectionService;

    @Inject
    private Event<ServerConnectionEvent> serverConnectionEvent;

    @Inject
    private Event<ServerMessageEvent> serverMessageEvent;

    @SneakyThrows
    public void sync(@Observes final  ServerConnectionEvent event){
        System.out.println("ServerConnectionHandler");
        final Socket socket = server.getServerSocket().accept();
        connectionService.add(socket);
        serverMessageEvent.fireAsync(new ServerMessageReadEvent(socket, ""));
        serverConnectionEvent.fire(new ServerConnectionEvent());

    }
}
