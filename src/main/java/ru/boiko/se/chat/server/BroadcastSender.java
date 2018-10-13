package ru.boiko.se.chat.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.boiko.se.chat.packet.Packet;
import ru.boiko.se.chat.packet.PacketType;
import ru.boiko.se.chat.users.ActiveUsers;
import ru.boiko.se.chat.users.Users;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class BroadcastSender implements Runnable{
    private DataInputStream incomingMessage;
    private DataOutputStream outgoingMessage;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public BroadcastSender(Socket socket) {
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
                if (packet.getType() == PacketType.LOGIN) { login(packet); }
                if (packet.getType() == PacketType.REGISTRY) { registry(packet); }
                if (packet.getType() == PacketType.MESSAGE) { message(packet); }
            } catch (Exception e) {
                send(currentMessage);
            }

        }
        run();
    }

    @SneakyThrows
    private void registry(Packet packet) {
        Packet requestPacket = new Packet();
        if(!Users.getInstance().exists(packet.getLogin())) {
            if(Users.getInstance().regisrty(packet)) {
                requestPacket.setLogin(packet.getLogin());
                requestPacket.setType(PacketType.REGISTRY);
                requestPacket.setSuccess(true);
                requestPacket.setMessage("Пользователь " + packet.getLogin() + " успешно зарегистрирован");
            }
        } else {
            requestPacket = packet;
            requestPacket.setMessage("Пользователь " + packet.getLogin() + " уже зарегистрирован");
            requestPacket.setSuccess(false);
        }
        send(objectMapper.writeValueAsString(requestPacket));
    }

    @SneakyThrows
    private void login(Packet packet) {
        Packet requestPacket = new Packet();
        if (Users.getInstance().check(packet.getLogin(), packet.getPassword())) {
            requestPacket.setId(packet.getId());
            requestPacket.setMessage("Авторизация пользователя " + packet.getLogin() + " прошла успешно");
            requestPacket.setType(PacketType.LOGIN);
            requestPacket.setSuccess(true);
            requestPacket.setLogin(packet.getLogin());
            requestPacket.setPassword(packet.getPassword());
            ActiveUsers.getInstance().getActiveUsers().put(packet.getLogin(), outgoingMessage);
            changeUserList();
        } else {
            requestPacket.setId(packet.getId());
            requestPacket.setMessage("Пользователь не найден или пароль введен не верно!");
            requestPacket.setType(PacketType.LOGIN);
            requestPacket.setSuccess(false);
        }
        send(objectMapper.writeValueAsString(requestPacket));
    }

    @SneakyThrows
    private void changeUserList() {
        Packet requestPacket = new Packet();
        requestPacket.setType(PacketType.REFRESH_USER_LIST);
        String message = "";
        for (HashMap.Entry<String, DataOutputStream> entry : ActiveUsers.getInstance().getActiveUsers().entrySet()) {

            message += entry.getKey() + ",,";
        }
        requestPacket.setMessage(message);
        for (HashMap.Entry<String, DataOutputStream> entry : ActiveUsers.getInstance().getActiveUsers().entrySet()) {
            DataOutputStream currentMessage = entry.getValue();
            currentMessage.writeUTF(objectMapper.writeValueAsString(requestPacket));
            System.out.println("Пользователю " + entry.getKey() + " отправлен список пользователей");
        }
    }

    @SneakyThrows
    private void message(Packet packet) {
        for (HashMap.Entry<String, DataOutputStream> entry : ActiveUsers.getInstance().getActiveUsers().entrySet()) {
            DataOutputStream currentMessage = entry.getValue();
            currentMessage.writeUTF(objectMapper.writeValueAsString(packet));
            System.out.println("Пользователю " + entry.getKey() + " отправлен список пользователей");
        }
    }

    @SneakyThrows
    private void send(String message) {
        outgoingMessage.writeUTF(message);
        System.out.println(message);
    }


}
