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

    public Client() {
        Config config = new Config();
        host = config.getHost();
        port = config.getSocket();
    }

    @SneakyThrows
    public void run() {
        socket = new Socket(host, port);
        in = new Scanner(socket.getInputStream());
        inMsg = new Scanner(System.in);
        out = new PrintWriter(socket.getOutputStream());

        new Thread(new Runnable() {
            @Override
            @SneakyThrows
            public void run() {
                while (true) {
                    if (inMsg.hasNext()) {
                        String message = inMsg.nextLine();
                        if ("end".equalsIgnoreCase(message)) break;
                        //System.out.println(message);
                        sendMsg(message);
                    }
                    if (in.hasNext()) {
                        String message = in.nextLine();
                        //if ("end".equalsIgnoreCase(message)) break;
                        System.out.println(message);
                        //sendMsg(message);
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
