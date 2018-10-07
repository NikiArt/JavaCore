package ru.boiko.se.lessonseven.server.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.NotNull;
import lombok.SneakyThrows;
import ru.boiko.se.lessonseven.server.ConnectionService;
import ru.boiko.se.lessonseven.server.UserService;
import ru.boiko.se.lessonseven.server.events.ServerRegistryEvent;
import ru.boiko.se.lessonseven.server.model.PacketRegistry;

import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.net.Socket;

public class ServerRegistryHandler {

    @Inject
    private UserService userService;

    @Inject
    private ConnectionService connectionService;

    @SneakyThrows
    public void handler(@ObservesAsync final ServerRegistryEvent event) {
        @NotNull final Socket socket = event.getSocket();
        @NotNull final String message = event.getMessage();
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketRegistry packet = objectMapper.readValue(message, PacketRegistry.class);
        final boolean result = userService.regisrty(packet.getLogin(), packet.getPassword());
        connectionService.sendResult(socket, result);
    }

}
