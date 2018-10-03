package ru.boiko.se.lessonsix.client;

import lombok.SneakyThrows;
import ru.boiko.se.lessonsix.Config;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String host;
    private final int port;
    private final Socket socket;
    private final Scanner in;
    private final Scanner inMsg;
    private final PrintWriter out;

    @SneakyThrows
    public Client() {
        final Config config = new Config();
        host = config.getHost();
        port = config.getSocket();
        socket = new Socket(host, port);
        in = new Scanner(socket.getInputStream());
        inMsg = new Scanner(System.in);
        out = new PrintWriter(socket.getOutputStream());
    }

    @SneakyThrows
    public final void run() {


        new Thread(new Runnable() {
            @Override
            @SneakyThrows
            public void run() {
                while (true) {
                    if (inMsg.hasNext()) {
                        String message = inMsg.nextLine();
                        if ("end".equalsIgnoreCase(message)) break;
                        sendMsg(message);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            @SneakyThrows
            public void run() {
                while (true) {
                    if (in.hasNext()) {
                        String message = in.nextLine();
                        System.out.println(message);
                    }
                }
            }
        }).start();
    }

    private void sendMsg(final String message) {
        out.println(message);
        out.flush();
    }



}
