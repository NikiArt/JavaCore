package ru.boiko.se.lessonsix.server;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class BroadcastSender implements Runnable {
    private final List<PrintWriter> printWriterList;
    private final Scanner scanner;

    public BroadcastSender(final List<PrintWriter> printWriterList, final Scanner scanner) {
        this.printWriterList = printWriterList;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        String message = scanner.nextLine();
        if ("end".equals(message)) {
            message = "Клиент отключился";
            scanner.close();
            sendMessage(message);
            return;
        }
        sendMessage(message);
        run();

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
