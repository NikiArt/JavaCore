package ru.boiko.se.lessonsix;

import ru.boiko.se.lessonsix.client.Client;
import ru.boiko.se.lessonsix.server.Server;

public class App {
    public static void main(String[] args) {
        getApp(args);
    }

    private static void getApp(final String[] args){
        if(args == null || args.length == 0){
            final Client clientRun = new Client();
            clientRun.run();
        }
        if(args.length == 1 && "server".equals(args[0])) {
            final Server serverRun = new Server();
            serverRun.run();
        }

    }
}
