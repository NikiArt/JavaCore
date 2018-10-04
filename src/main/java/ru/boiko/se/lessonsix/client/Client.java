package ru.boiko.se.lessonsix.client;

import lombok.SneakyThrows;
import ru.boiko.se.lessonsix.Config;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private final String host;
    private final int port;
    private final Socket socket;
    private final Scanner in;
    private final Scanner inMsg;
    private final PrintWriter out;
    private final ExecutorService executor;

    @SneakyThrows
    public Client() {
        final Config config = new Config();
        host = config.getHost();
        port = config.getSocket();
        socket = new Socket(host, port);
        in = new Scanner(socket.getInputStream());
        inMsg = new Scanner(System.in);
        out = new PrintWriter(socket.getOutputStream());
        executor = Executors.newCachedThreadPool();
    }

    @SneakyThrows
    public final void run() {

        executor.submit(new MessageSender(socket));
        executor.submit(new SteamWriter(socket));
    }

    private void sendMsg(final String message) {
        out.println(message);
        out.flush();
    }



}
