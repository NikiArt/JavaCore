package ru.boiko.se.chat.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.*;
import lombok.SneakyThrows;
import ru.boiko.se.chat.packets.Packet;
import ru.boiko.se.chat.packets.PacketType;

import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MessageSender implements Runnable {
    private final Scanner inMessage;
    private final PrintWriter outMessage;
    private final DataOutputStream stream;

    @SneakyThrows
    public MessageSender(final Socket socket) {
        inMessage = new Scanner(System.in);
        stream = new DataOutputStream(socket.getOutputStream());
        /*stream.writeUTF(message);*/
        outMessage = new PrintWriter(socket.getOutputStream());
    }

    @Override
    @SneakyThrows
    public void run() {
        if (inMessage.hasNext()) {
           // System.out.println("message");
            String message = inMessage.nextLine();
            /*@NotNull final ObjectMapper objectMapper = new ObjectMapper();
            @NotNull final Packet packet = new Packet();
            packet.setType(PacketType.REGISTRY);
            System.out.println("login");
            packet.setLogin(inMessage.nextLine());
            System.out.println("password");
            packet.setPassword(inMessage.nextLine());
            packet.setMessage(message);*/
 /*           if ("end".equalsIgnoreCase(message)) {
                sendMsg(message);
                System.out.println("Вы отключились от чата");
                return;
            }*/
            //sendMsg(objectMapper.writeValueAsString(packet));
            sendMsg(message);
        }
        run();
    }

    @SneakyThrows
    private void sendMsg(final String message) {
        /*outMessage.println(message);
        outMessage.flush();*/
        stream.writeUTF(message);
    }
}
