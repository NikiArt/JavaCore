package ru.boiko.se.chat.client;

import lombok.SneakyThrows;

import java.net.Socket;
import java.util.Scanner;

public class StreamWriter implements Runnable {
    private final Scanner broadcastMessage;

    @SneakyThrows
    public StreamWriter(final Socket socket){
        broadcastMessage = new Scanner(socket.getInputStream());
    }

    @Override
    public void run() {
        if (broadcastMessage.hasNext()) {
            String message = broadcastMessage.nextLine();
            System.out.println(message);
        }
        run();
    }
}
