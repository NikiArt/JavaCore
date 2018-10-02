package ru.boiko.se.lessonsix.client;

import lombok.SneakyThrows;
import ru.boiko.se.lessonsix.Config;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String host;
    private final int port;
    private Socket sock;
    private Scanner in;
    private PrintWriter out;

    public Client() {
        Config config = new Config();
        host = config.getHost();
        port = config.getSocket();
    }

    @SneakyThrows
    public void run() {
        sock = new Socket(host, port);
        in = new Scanner(sock.getInputStream());
        out = new PrintWriter(sock.getOutputStream());

        new Thread(new Runnable() {
            @Override
            @SneakyThrows
            public void run() {
                while (true) {
                    if (in.hasNext()) {
                        String message = in.nextLine();
                        if ("end".equalsIgnoreCase(message)) break;
                        System.out.println(message);
                        sendMsg(message);
                    }
                }
            }
        }).start();
    }

    public void sendMsg(String message) {
        out.println();
        out.flush();
    }



}
