package ru.boiko.se.lessonsix.server;

/**
 * инициализация серверной части приложения
 * @see ru.boiko.se.lessonsix.server.SteamRunner#run()  - открытие подключения для клиента
 * @see ru.boiko.se.lessonsix.server.BroadcastSender#run() - широковещательная рассылка сообщений клиентам
 * @see ru.boiko.se.lessonsix.server.BroadcastSender#printWriterList - список активных каналов подключенных клиентов для отправки сообщений
 * для сервера необходимо в main передать аругмент String[] = "server"
 */

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import ru.boiko.se.lessonsix.Config;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
@Setter
public class Server {
    private final ExecutorService executor;
    private final SteamRunner serverRun;
    private final ServerSocket serverSocket;
    private final Config config;

    @SneakyThrows
    public Server() {
        executor = Executors.newCachedThreadPool();
        this.config = new Config();
        serverRun = new SteamRunner(this);
        serverSocket = new ServerSocket(config.getSocket());
        System.out.println("Сервер запущен, ожидаем подключения...");
    }

    @SneakyThrows
    public final void run() {
            executor.submit(serverRun);
    }
}
