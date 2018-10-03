package ru.boiko.se.lessonsix.client;

import lombok.SneakyThrows;
import ru.boiko.se.lessonsix.Config;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String host;
    private final int port;
    private Socket socket;
    private Scanner in;
    private Scanner inMsg;
    private PrintWriter out;

    @SneakyThrows
    public Client() {
        Config config = new Config();
        host = config.getHost();
        port = config.getSocket();
        socket = new Socket(host, port);
        in = new Scanner(socket.getInputStream());
        inMsg = new Scanner(System.in);
        out = new PrintWriter(socket.getOutputStream());
    }

    @SneakyThrows
    public void run() {


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

    public void sendMsg(String message) {
        out.println(message);
        out.flush();
    }



}
