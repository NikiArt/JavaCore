package ru.boiko.se.lessonseven.server.handlers;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import ru.boiko.se.lessonseven.server.Connection;
import ru.boiko.se.lessonseven.server.ConnectionService;
import ru.boiko.se.lessonseven.server.events.ServerBroadcastEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.net.Socket;

@ApplicationScoped
public class ServerBroadcastHandler {

    @Inject
    private ConnectionService connectionService;

    public void handler(@ObservesAsync final ServerBroadcastEvent event) {
        @NotNull final Socket socket = event.getSocket();
        @Nullable final Connection connection = connectionService.get(socket);
        if(connection == null) return;
        @Nullable final String login = connection.getLogin();
        if(login == null || login.isEmpty()) return;
        @NotNull final String message = event.getMessage();
        for(final Connection item: connectionService.connections()) {
            connectionService.sendMessage(item, login, message);
        }
    }
}
