package ru.boiko.se.lessonsix.server;

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
    private SteamRunner serverRun;
    private ServerSocket serverSocket;
    private Config config;

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
