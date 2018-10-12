package ru.boiko.se.chat.server;

import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StreamRunner implements Runnable {
    private final Server server;
    private final ExecutorService executor;
    private final ActiveUsers activeUsers = ActiveUsers.getInstance();

    @SneakyThrows
    public StreamRunner(final Server server) {
        this.server = server;
        executor = Executors.newCachedThreadPool();
    }

    @Override
    @SneakyThrows
    public void run() {
        final Socket socket = this.server.getServerSocket().accept();
        System.out.println("Клиент подключился");
       // final Scanner scanner = new Scanner(socket.getInputStream());
        final DataInputStream scanner = new DataInputStream(socket.getInputStream());
        Connection currentConnection = new Connection(socket);
        activeUsers.getActiveUsers().add(currentConnection);
        executor.submit(new BroadcastSender(scanner, socket, currentConnection));
        this.server.run();
    }
}
