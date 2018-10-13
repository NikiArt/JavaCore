package ru.boiko.se.chatNew.server;

import lombok.SneakyThrows;
import ru.boiko.se.chatNew.packet.Packet;
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
        Packet packetAdmin = new Packet();
        Packet packetTest = new Packet();
        packetAdmin.setLogin("admin");
        packetAdmin.setPassword("admin");
        packetAdmin.setNick("admin");
        packetTest.setLogin("test");
        packetTest.setPassword("test");
        packetTest.setNick("test");
        Users.getInstance().regisrty(packetAdmin);
        Users.getInstance().regisrty(packetTest);
    }

    @SneakyThrows
    public final void run() {
        executor.submit(connections);
    }
}
