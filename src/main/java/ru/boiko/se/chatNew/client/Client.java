package ru.boiko.se.chatNew.client;

import lombok.SneakyThrows;
import ru.boiko.se.chatNew.Config;
import ru.boiko.se.chatNew.client.gui.ChatWindow;
import ru.boiko.se.chatNew.client.gui.LoginWindow;
import ru.boiko.se.chatNew.client.gui.RegistryWindow;
import ru.boiko.se.chatNew.client.gui.WorkWindows;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private Socket socket;
    private ExecutorService executor;
    private MessageSender messageSender;

    private LoginWindow loginWindow;
    private ChatWindow chatWindow;
    private RegistryWindow registryWindow;

    @SneakyThrows
    public Client() {
        executor = Executors.newCachedThreadPool();
        socket = new Socket(Config.getInstance().getHost(), Config.getInstance().getPort());
        messageSender = new MessageSender(socket);

        windowsInit();
    }

    private void windowsInit() {
        loginWindow = new LoginWindow(messageSender);
        WorkWindows.getInstance().setLoginWindow(loginWindow);
        chatWindow = new ChatWindow(messageSender);

        WorkWindows.getInstance().setChatWindow(chatWindow);
        registryWindow = new RegistryWindow(messageSender);
        WorkWindows.getInstance().setRegistryWindow(registryWindow);
    }

    @SneakyThrows
    public final void run() {
        executor.submit(messageSender);
        executor.submit(new MessageListener(socket));
        loginWindow.setVisible(true);
    }
}
