package ru.boiko.se.lessonseven.server.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.NotNull;
import lombok.SneakyThrows;
import ru.boiko.se.lessonseven.server.ConnectionService;
import ru.boiko.se.lessonseven.server.UserService;
import ru.boiko.se.lessonseven.server.events.ServerLoginEvent;
import ru.boiko.se.lessonseven.server.model.PacketLogin;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.net.Socket;

@ApplicationScoped
public class ServerLoginHandler {

    @Inject
    private UserService userService;

    @Inject
    private ConnectionService connectionService;

    @SneakyThrows
    public void handler(@ObservesAsync final ServerLoginEvent event) {
        @NotNull final Socket socket = event.getSocket();
        @NotNull final String message = event.getMessage();
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketLogin packet = objectMapper.readValue(message, PacketLogin.class);
        boolean check = userService.check(packet.getLogin(), packet.getPassword());
        if (check) connectionService.setLogin(socket, packet.getLogin());
        if(!check) System.out.println("Incorrect password for login: " + packet.getLogin());
        connectionService.sendResult(socket, check);
    }
}
