package ru.boiko.se.chat.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.*;
import lombok.SneakyThrows;
import ru.boiko.se.chat.packets.Packet;

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
    @SneakyThrows
    public void run() {
        if (inMessage.hasNext()) {
            String message = inMessage.nextLine();
            @NotNull final ObjectMapper objectMapper = new ObjectMapper();
            @NotNull final Packet packet = new Packet();
            packet.setMessage(message);
 /*           if ("end".equalsIgnoreCase(message)) {
                sendMsg(message);
                System.out.println("Вы отключились от чата");
                return;
            }*/
            sendMsg(objectMapper.writeValueAsString(packet));
        }
        run();
    }

    private void sendMsg(final String message) {
        outMessage.println(message);
        outMessage.flush();
    }
}
