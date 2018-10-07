package ru.boiko.se.lessonseven.client.Handlers;

import com.sun.istack.internal.NotNull;
import ru.boiko.se.lessonseven.client.Client;
import ru.boiko.se.lessonseven.client.Events.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Scanner;

@ApplicationScoped
public class ClientMessageInputHandler {
    @NotNull
    private final static String CMD_EXIT = "exit";

    @NotNull
    private final static String CMD_LOGIN = "login";

    @NotNull
    private final static String CMD_PING = "ping";

    @NotNull
    private final static String CMD_BROADCAST = "broadcast";

    @NotNull
    private final static String CMD_REGISTRY = "registry";

    @Inject
    private Client client;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @Inject
    private Event<ClientBroadcastEvent> clientBroadcastEvent;

    @Inject
    private Event<ClientPingEvent> clientPingEvent;

    @Inject
    private Event<ClientLoginEvent> clientLoginEvent;

    @Inject
    private Event<ClientRegistryEvent> clientRegistryEvent;

    public void handler(@Observes final ClientMessageInputEvent event) {
        System.out.println("Enter cmd (message or exit)");
        @NotNull final Scanner in = new Scanner(System.in);
        @NotNull final String message = in.nextLine();

        if (CMD_PING.equals(message)) {
            clientPingEvent.fireAsync(new ClientPingEvent());
            clientMessageInputEvent.fire(new ClientMessageInputEvent());
            return;
        }

        if (CMD_LOGIN.equals(message)) {
            clientLoginEvent.fire(new ClientLoginEvent());
            return;
        }

        if (CMD_BROADCAST.equals(message)) {
            clientBroadcastEvent.fire(new ClientBroadcastEvent());
            return;
        }

        if (CMD_REGISTRY.equals(message)) {
            clientRegistryEvent.fire(new ClientRegistryEvent());
            return;
        }

        if (CMD_EXIT.equals(message)) {
            client.exit();
            return;
        }

        System.out.println();
        clientMessageInputEvent.fire(new ClientMessageInputEvent());
    }
}
