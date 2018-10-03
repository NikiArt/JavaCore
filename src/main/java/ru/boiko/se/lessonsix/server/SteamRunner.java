package ru.boiko.se.lessonsix.server;

import lombok.SneakyThrows;
import ru.boiko.se.lessonsix.Config;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SteamRunner implements Runnable{
    private Server server;
    private List<PrintWriter> printWriterList;

    @SneakyThrows
    public SteamRunner(Server server) {
        this.server = server;
        this.printWriterList = new ArrayList<PrintWriter>();
    }

    @Override
    @SneakyThrows
    public void run(){
        Socket socket = this.server.getServerSocket().accept();
        System.out.println("Клиент подключился");
        Scanner sc = new Scanner(socket.getInputStream());
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        printWriterList.add(pw);
        this.server.run();
        while (true) {
            String str = sc.nextLine();
            if (str.equals("end")) break;
            for(int i = 0; i < printWriterList.size(); i++) {
                PrintWriter currentPw = printWriterList.get(i);
                currentPw.println("Эхо: " + str);
            }
            System.out.println("Эхо: " + str);
            pw.flush();
        }
    }
}
