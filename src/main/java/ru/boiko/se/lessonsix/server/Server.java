package ru.boiko.se.lessonsix.server;

import lombok.SneakyThrows;
import ru.boiko.se.lessonsix.Config;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    Config config;

    public Server(){
        this.config = new Config();
    }

    @SneakyThrows
    public final void run() {
        ServerSocket server = null;
        Socket socket = null;
            server = new ServerSocket(config.getSocket());
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = server.accept();
            System.out.println("Клиент подключился");
            Scanner sc = new Scanner(socket.getInputStream());
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            while (true) {
                String str = sc.nextLine();
                if (str.equals("end")) break;
                pw.println("Эхо: " + str);
                System.out.println("Эхо: " + str);
                pw.flush();
            }

            server.close();
    }
}
