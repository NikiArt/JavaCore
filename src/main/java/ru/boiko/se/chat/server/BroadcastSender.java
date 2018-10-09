package ru.boiko.se.chat.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.boiko.se.chat.packets.Packet;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class BroadcastSender implements Runnable {
    private final List<PrintWriter> printWriterList;
    private final Scanner scanner;
    private final Socket Socket;
    private final Users users = Users.getInstance();

    public BroadcastSender(final List<PrintWriter> printWriterList, final Scanner scanner, Socket Socket) {
        this.printWriterList = printWriterList;
        this.scanner = scanner;
        this.Socket = Socket;
    }

    @Override
    @SneakyThrows
    public void run() {
            String message = scanner.nextLine();
            final ObjectMapper objectMapper = new ObjectMapper();
            final Packet packet = objectMapper.readValue(message, Packet.class);
            switch (packet.getType()) {
                case MESSAGE:
                    broadcastMessage(packet);
                case LOGIN:
                    loginClient(packet);
                case REGISTRY:
                    regisrtyCliaent(packet);
            }
            if ("end".equals(message)) {
                message = "Клиент отключился";
                scanner.close();
                sendMessage(message);
                return;
            }
            sendMessage(message);
            run();
    }

    private void regisrtyCliaent(Packet packet) {
            if(users.regisrty(packet.getLogin(), packet.getPassword())) {

            };
    }

    private void loginClient(Packet packet) {
        User registredMember = users.findByLogin(packet.getLogin());
    }

    private void broadcastMessage(Packet packet) {

    }

    private void sendBroadcastMessage(final String message) {
        for(int i = 0; i < printWriterList.size(); i++) {
            final PrintWriter currentPw = printWriterList.get(i);
            currentPw.println(message);
            currentPw.flush();
        }
        System.out.println(message);
    }

    private void sendMessage(final String message) {
        for(int i = 0; i < printWriterList.size(); i++) {
            final PrintWriter currentPw = printWriterList.get(i);
            currentPw.println(message);
            currentPw.flush();
        }
        System.out.println(message);
    }
}
