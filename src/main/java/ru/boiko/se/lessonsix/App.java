package ru.boiko.se.lessonsix;

/**
 * Домашнее задание к уроку 06
 * @author Никита Бойко
 * @version 1.4
 * @see ru.boiko.se.lessonsix.App#getApp(java.lang.String[]) - Метод входа в создание клиентской или серверной части
 * для сервера необходимо в main передать аругмент String[] = "server"
 */

import ru.boiko.se.lessonsix.client.Client;
import ru.boiko.se.lessonsix.server.Server;

public class App {
    public static void main(String[] args) {
        getApp(args);
    }

    private static void getApp(final String[] args){
        if(args == null || args.length == 0) {
            try {
                final Client clientRun = new Client();
                clientRun.run();
            } catch(Exception e) {
                System.out.println("Не удалось установить соединение с сервером");
            }
        }
        if(args.length == 1 && "server".equals(args[0])) {
            final Server serverRun = new Server();
            serverRun.run();
        }
    }
}
