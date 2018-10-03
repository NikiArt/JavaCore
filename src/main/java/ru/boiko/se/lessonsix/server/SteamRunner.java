package ru.boiko.se.lessonsix.server;

import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SteamRunner implements Runnable{
    private final Server server;
    private final List<PrintWriter> printWriterList;

    @SneakyThrows
    public SteamRunner(final Server server) {
        this.server = server;
        this.printWriterList = new ArrayList<PrintWriter>();
    }

    @Override
    @SneakyThrows
    public void run(){
        final Socket socket = this.server.getServerSocket().accept();
        System.out.println("Клиент подключился");
        final Scanner sc = new Scanner(socket.getInputStream());
        final PrintWriter pw = new PrintWriter(socket.getOutputStream());
        printWriterList.add(pw);
        this.server.run();
        while (true) {
            String str = sc.nextLine();
            if (str.equals("end")) break;
            for(int i = 0; i < printWriterList.size(); i++) {
                PrintWriter currentPw = printWriterList.get(i);
                currentPw.println("Эхо: " + str);
                currentPw.flush();
            }
            System.out.println("Эхо: " + str);

        }
    }
}
