package ru.boiko.se.chat.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.boiko.se.chat.packets.Packet;
import ru.boiko.se.chat.packets.PacketType;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class BroadcastSender implements Runnable {
    private final DataInputStream scanner;
    private final Users users = Users.getInstance();
    private final ActiveUsers activeUsers = ActiveUsers.getInstance();
    private final Connection currentConnection;
    private final Socket socket;

    public BroadcastSender(DataInputStream scanner, Socket socket, Connection currentConnection){
        this.scanner = scanner;
        this.currentConnection = currentConnection;
        this.socket = socket;
    }

    @Override
    @SneakyThrows
    public void run() {
        //String message = scanner.nextLine();
        String message = scanner.readUTF();
        System.out.println(message);
        final ObjectMapper objectMapper = new ObjectMapper();
        final Packet packet = objectMapper.readValue(message, Packet.class);
        switch (packet.getType()) {
            case MESSAGE:
                broadcastMessage(packet);
                break;
            case LOGIN:
                loginClient(packet);
                break;
            case REGISTRY:
                regisrtyCliaent(packet);
                break;
        }
        run();
    }

    @SneakyThrows
    private void regisrtyCliaent(Packet packet) {
            if(users.regisrty(packet.getLogin(), packet.getPassword())) {
                final  Packet requestPacket = new Packet();
                requestPacket.setId(packet.getId());
                requestPacket.setType(PacketType.REGISTRY);
                requestPacket.setMessage("Success");
                currentConnection.send(new ObjectMapper().writeValueAsString(requestPacket));
            };
    }

    @SneakyThrows
    private void loginClient(Packet packet) {
            User registredMember = users.findByLogin(packet.getLogin());
            DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
            final Packet requestPacket = new Packet();
            requestPacket.setId(packet.getId());
            requestPacket.setType(PacketType.LOGIN);
            if (registredMember == null) {
                requestPacket.setMessage("Error. User not found");
            } else {
            /*if (activeUsers.getActiveUsers().contains(registredMember)) {
                requestPacket.setMessage("Error. The user is already connected");
            } else {*/
                requestPacket.setMessage("Success. " + registredMember.getLogin());
                currentConnection.setUser(registredMember);
                //}
            }
            stream.writeUTF(new ObjectMapper().writeValueAsString(requestPacket));
            stream.flush();
        //currentConnection.send(new ObjectMapper().writeValueAsString(requestPacket));
    }

    @SneakyThrows
    private void broadcastMessage(Packet packet) {
        final  Packet requestPacket = new Packet();
        requestPacket.setId(packet.getId());
        requestPacket.setType(PacketType.MESSAGE);
        requestPacket.setMessage(packet.getMessage());
        for (Connection connection: ActiveUsers.getInstance().getActiveUsers()) {
            connection.send(new ObjectMapper().writeValueAsString(requestPacket));
        }
    }

}
