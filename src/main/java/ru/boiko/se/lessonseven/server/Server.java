package ru.boiko.se.lessonseven.server;

/**
 * инициализация серверной части приложения
 * @see ru.boiko.se.lessonsix.server.SteamRunner#run()  - открытие подключения для клиента
 * @see ru.boiko.se.lessonsix.server.BroadcastSender#run() - широковещательная рассылка сообщений клиентам
 * @see ru.boiko.se.lessonsix.server.BroadcastSender#printWriterList - список активных каналов подключенных клиентов для отправки сообщений
 * для сервера необходимо в main передать аругмент String[] = "server"
 */

import lombok.Getter;
import lombok.SneakyThrows;
import ru.boiko.se.lessonseven.Config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.net.ServerSocket;

@Getter
@ApplicationScoped
public class Server {

    @Inject
    private Config config;

    @Inject
    private Event<ServerConnectionEvent> serverConnectionEvent;

    private ServerSocket serverSocket;

    @SneakyThrows
    public Server() {
        /*executor = Executors.newCachedThreadPool();
        this.config = new Config();
        serverRun = new SteamRunner(this);
        serverSocket = new ServerSocket(config.getSocket());
        System.out.println("Сервер запущен, ожидаем подключения...");*/
        serverSocket = new ServerSocket(config.getSocket());
    }

    @SneakyThrows
    public final void run() {
        //executor.submit(serverRun);
        serverConnectionEvent.fire(new ServerConnectionEvent());
    }
}
