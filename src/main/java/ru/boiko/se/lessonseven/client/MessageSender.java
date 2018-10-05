package ru.boiko.se.lessonseven.client;

import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MessageSender implements Runnable {
    private final Scanner inMessage;
    private final PrintWriter outMessage;

    @SneakyThrows
    public MessageSender(final Socket socket) {
        inMessage = new Scanner(System.in);
        outMessage = new PrintWriter(socket.getOutputStream());
    }

    @Override
    public void run() {
        if (inMessage.hasNext()) {
            String message = inMessage.nextLine();
            if ("end".equalsIgnoreCase(message)) {
                sendMsg(message);
                System.out.println("Вы отключились от чата");
                return;
            }
            sendMsg(message);
        }
        run();
    }

    private void sendMsg(final String message) {
        outMessage.println(message);
        outMessage.flush();
    }
}
