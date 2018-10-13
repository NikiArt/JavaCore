package ru.boiko.se.chatNew.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.boiko.se.chatNew.client.gui.WorkWindows;
import ru.boiko.se.chatNew.packet.Packet;

import javax.swing.*;
import java.io.DataInputStream;
import java.net.Socket;

public class MessageListener implements Runnable{
    private DataInputStream incomingMessage;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public MessageListener(Socket socket) {
        incomingMessage = new DataInputStream(socket.getInputStream());
    }

    @Override
    @SneakyThrows
    public void run() {
        String currentMessage = incomingMessage.readUTF();
        if (!currentMessage.isEmpty()) {
            Packet packet = objectMapper.readValue(currentMessage, Packet.class);
            switch (packet.getType()) {
                case LOGIN:
                    login(packet);
            }
        }
        run();
    }

    private void login(Packet packet) {
        if (packet.getSuccess()) {
            WorkWindows.getInstance().getLoginWindow().setVisible(false);
            WorkWindows.getInstance().getChatWindow().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(WorkWindows.getInstance().getLoginWindow(),packet.getMessage());
        }
    }
}
