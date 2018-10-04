package ru.boiko.se.lessonsix.server;

import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SteamRunner implements Runnable{
    private final Server server;
    private final List<PrintWriter> printWriterList = new ArrayList<>();
    private final ExecutorService executor;

    @SneakyThrows
    public SteamRunner(final Server server) {
        this.server = server;
        executor = Executors.newCachedThreadPool();

    }

    @Override
    @SneakyThrows
    public void run(){
        final Socket socket = this.server.getServerSocket().accept();
        System.out.println("Клиент подключился");
        final Scanner scanner = new Scanner(socket.getInputStream());
        final PrintWriter pw = new PrintWriter(socket.getOutputStream());
        printWriterList.add(pw);
        executor.submit(new BroadcastSender(printWriterList, scanner));
        this.server.run();
    }
}
