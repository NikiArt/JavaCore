package ru.boiko.se.lessonsix.server;

import lombok.SneakyThrows;
import ru.boiko.se.lessonsix.Config;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SteamRunner implements Runnable{
    private Server server;
    private Config config;

    @SneakyThrows
    public SteamRunner(Server server) {
        this.server = server;
        this.config = new Config();
    }

    @Override
    @SneakyThrows
    public void run(){
        /*ServerSocket server = new ServerSocket(config.getSocket());
        System.out.println("Сервер запущен, ожидаем подключения...");
        Socket socket = server.accept();*/
        Socket socket = this.server.getServerSocket().accept();
        System.out.println("Клиент подключился");
        Scanner sc = new Scanner(socket.getInputStream());
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        this.server.run();
        while (true) {
            String str = sc.nextLine();
            if (str.equals("end")) break;
            pw.println("Эхо: " + str);
            System.out.println("Эхо: " + str);
            pw.flush();
        }
    }
}
