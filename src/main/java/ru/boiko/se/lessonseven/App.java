package ru.boiko.se.lessonseven;

/**
 * Домашнее задание к уроку 06
 * @author Никита Бойко
 * @version 1.4
 * @see ru.boiko.se.lessonsix.App#getApp(java.lang.String[]) - Метод входа в создание клиентской или серверной части
 * для сервера необходимо в main передать аругмент String[] = "server"
 */

import ru.boiko.se.lessonseven.client.Client;
import ru.boiko.se.lessonseven.server.Server;

import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;

public class App {
    public static void main(String[] args) {
        getApp(args);
    }

    private static void getApp(final String[] args){
        SeContainerInitializer.newInstance().addPackages(App.class).initialize();
       if(args == null || args.length == 0) {
           CDI.current().select(Client.class).get().run();
            /*try {
                final Client clientRun = new Client();
                clientRun.run();
            } catch(Exception e) {
                System.out.println("Не удалось установить соединение с сервером");
            }*/
        }
        if(args.length == 1 && "server".equals(args[0])) {
            CDI.current().select(Server.class).get().run();
            /*final Server serverRun = new Server();
            serverRun.run();*/
        }
    }
}
