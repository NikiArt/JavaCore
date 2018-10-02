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
        ServerSocket serv = null;
        Socket sock = null;
            serv = new ServerSocket(config.getSocket());
            System.out.println("Сервер запущен, ожидаем подключения...");
            sock = serv.accept();
            System.out.println("Клиент подключился");
            Scanner sc = new Scanner(sock.getInputStream());
            PrintWriter pw = new PrintWriter(sock.getOutputStream());
            while (true) {
                String str = sc.nextLine();
                if (str.equals("end")) break;
                pw.println("Эхо: " + str);
                pw.flush();
            }

            serv.close();
    }
}
