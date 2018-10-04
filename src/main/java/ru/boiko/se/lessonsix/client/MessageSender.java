package ru.boiko.se.lessonsix.client;

import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MessageSender implements Runnable {
    private final Scanner inMsg;
    private final PrintWriter out;

    @SneakyThrows
    public MessageSender(Socket socket){
        inMsg = new Scanner(System.in);
        out = new PrintWriter(socket.getOutputStream());
    }

    @Override
    public void run() {
        if (inMsg.hasNext()) {
            String message = inMsg.nextLine();
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
        out.println(message);
        out.flush();
    }
}
