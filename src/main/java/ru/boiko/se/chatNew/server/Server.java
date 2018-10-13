package ru.boiko.se.chatNew.server;

import lombok.SneakyThrows;
import ru.boiko.se.chatNew.users.Users;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ExecutorService executor;
    private final Connections connections;

    @SneakyThrows
    public Server() {
        executor = Executors.newCachedThreadPool();
        connections = new Connections(executor);
        defaultUsers();
        System.out.println("Сервер запущен, ожидаем подключения...");
    }

    private void defaultUsers() {
        Users.getInstance().regisrty("admin", "admin");
        Users.getInstance().regisrty("test", "test");
    }

    @SneakyThrows
    public final void run() {
        executor.submit(connections);
    }
}
