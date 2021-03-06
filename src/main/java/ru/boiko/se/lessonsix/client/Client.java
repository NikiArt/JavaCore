package ru.boiko.se.lessonsix.client;

/**
 * инициализация клиентской части приложения
 * @see ru.boiko.se.lessonsix.client.MessageSender#run()   - отправка сообщения серверу
 * @see ru.boiko.se.lessonsix.client.SteamWriter#run()  - обработка входящего сообщения и вывод на экран
 * для сервера необходимо в main передать аругмент String[] = "server"
 */

import lombok.SneakyThrows;
import ru.boiko.se.lessonsix.Config;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private Socket socket;
    private final ExecutorService executor;

    @SneakyThrows
    public Client() {
        final Config config = new Config();
        final String host = config.getHost();
        final int port = config.getSocket();
        socket = null;
        executor = Executors.newCachedThreadPool();
        socket = new Socket(host, port);
    }

    @SneakyThrows
    public final void run() {
        executor.submit(new MessageSender(socket));
        executor.submit(new SteamWriter(socket));
    }
}
