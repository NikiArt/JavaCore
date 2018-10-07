package ru.boiko.se.lessonseven.client;

/**
 * инициализация клиентской части приложения
 * @see ru.boiko.se.lessonsix.client.MessageSender#run()   - отправка сообщения серверу
 * @see ru.boiko.se.lessonsix.client.SteamWriter#run()  - обработка входящего сообщения и вывод на экран
 * для сервера необходимо в main передать аругмент String[] = "server"
 */

import lombok.SneakyThrows;
import ru.boiko.se.lessonseven.Config;
import ru.boiko.se.lessonseven.client.Events.ClientMessageInputEvent;
import ru.boiko.se.lessonseven.client.Events.ClientMessageReadEvent;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {

    @Inject
    private Event<ClientMessageReadEvent> clientMessageReadEvent;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @Inject
    private Config config;

    private Socket socket;

    private DataInputStream in;

    private DataOutputStream out;

    @SneakyThrows
    public final void run() {
        final  String host = config.getHost();
        final Integer port = config.getPort();
        socket = new Socket(host, port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        clientMessageReadEvent.fireAsync(new ClientMessageReadEvent());
        clientMessageInputEvent.fire(new ClientMessageInputEvent());

    }

    @SneakyThrows
    public void send(String message) { out.writeUTF(message); }

    @SneakyThrows
    public void exit() {
        socket.close();
        System.out.println("Chat client disconnected...");
        System.exit(0);
    }
}
