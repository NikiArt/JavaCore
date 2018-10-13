package ru.boiko.se.chatNew.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.boiko.se.chatNew.packet.Packet;
import ru.boiko.se.chatNew.packet.PacketType;
import ru.boiko.se.chatNew.users.Users;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class BroadcastSender implements Runnable{
    private final Socket socket;
    private DataInputStream incomingMessage;
    private DataOutputStream outgoingMessage;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public BroadcastSender(Socket socket) {
        this.socket = socket;
        incomingMessage = new DataInputStream(socket.getInputStream());
        outgoingMessage = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    @SneakyThrows
    public void run() {
        String currentMessage = incomingMessage.readUTF();
        System.out.println(currentMessage);
        if (!currentMessage.isEmpty()) {
            try {
                Packet packet = objectMapper.readValue(currentMessage, Packet.class);
                switch (packet.getType()) {
                    case LOGIN:
                        login(packet);
                }
            } catch (Exception e) {
                send(currentMessage);
            }

        }
        run();
    }

    @SneakyThrows
    private void login(Packet packet) {
        Packet requestPacket = new Packet();
        if (Users.getInstance().check(packet.getLogin(), packet.getPassword())) {
            requestPacket.setId(packet.getId());
            requestPacket.setMessage("Авторизация пользователя " + packet.getLogin() + " прошла успешно");
            requestPacket.setType(PacketType.LOGIN);
            requestPacket.setSuccess(true);
        } else {
            requestPacket.setId(packet.getId());
            requestPacket.setMessage("Пользователь не найден или пароль введен не верно!");
            requestPacket.setType(PacketType.LOGIN);
            requestPacket.setSuccess(false);
        }
        send(objectMapper.writeValueAsString(requestPacket));
    }

    @SneakyThrows
    private void send(String message) {
        outgoingMessage.writeUTF(message);
        System.out.println(message);
    }


}
