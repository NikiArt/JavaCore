package ru.boiko.se.chat.client;

import lombok.SneakyThrows;
import ru.boiko.se.chat.ui.LoginWindow;

import javax.swing.*;
import java.io.DataInputStream;
import java.net.Socket;

public class BroadcastListener implements Runnable{
    private Socket socket;
    private JFrame loginWindow;
    private DataInputStream incomingMessage;

    @SneakyThrows
    public BroadcastListener(Socket socket, JFrame loginWindow) {
        this.socket = socket;
        this.loginWindow = loginWindow;
        incomingMessage = new DataInputStream(socket.getInputStream());
    }

    @Override
    @SneakyThrows
    public void run() {
        String message = incomingMessage.readUTF();
        if (!message.isEmpty()) {
            System.out.println(message);
        }
        run();
    }
}
