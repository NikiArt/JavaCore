package ru.boiko.se.lessonseven.client.Handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.NotNull;
import lombok.SneakyThrows;
import ru.boiko.se.lessonseven.client.Client;
import ru.boiko.se.lessonseven.client.Events.ClientLoginEvent;
import ru.boiko.se.lessonseven.client.Events.ClientMessageInputEvent;
import ru.boiko.se.lessonseven.server.model.PacketLogin;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Scanner;

public class ClientLoginHandler {

    @Inject
    private Client client;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @SneakyThrows
    public void handler(@Observes final ClientLoginEvent event) {
        @NotNull final Scanner in = new Scanner(System.in);

        System.out.println("Enter login");
        @NotNull final String login = in.nextLine();

        System.out.println("Enter password");
        @NotNull final String password = in.nextLine();

        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final PacketLogin packet = new PacketLogin();
        packet.setLogin(login);
        packet.setPassword(password);

        client.send(objectMapper.writeValueAsString(packet));
        clientMessageInputEvent.fire(new ClientMessageInputEvent());
    }
}
