package ru.boiko.se.lessonseven.server;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionServiceBean implements ConnectionService {

    @NotNull
    private final List<Connection> connections = new ArrayList<>();

    @Override
    public List<Connection> connections() {
        return connections;
    }

    @Nullable
    @Override
    public Connection get(Socket socket) {
        if (socket == null) return null;
        for (final Connection connection: connections) {
            if(connection.getSocket().equals(socket)) return connection;
        }
        return  null;
    }

    @Override
    public void add(@Nullable final Socket socket) {
        if (socket == null) return;
        @NotNull final Connection connection = new Connection(socket);
        connections.add(connection);
        System.out.println("Added connection with id: " + connection.getId() + "...");

    }

    @Override
    public void remove(Socket socket) {
        if (socket == null) return;
        final Connection connection = get(socket);
        if (connection == null) return;

    }

    @Override
    public void setLogin(Socket socket, String login) {
        if (login == null || login.isEmpty()) return;
        @Nullable final Connection connection = get(socket);
        if (connection == null) return;
        connection.setLogin(login);
        System.out.println("Session created for login: " + login);

    }

    @Override
    public void sendResult(Socket socket, Boolean success) {

    }

    @Override
    public void sendMessage(Connection connection, String login, String message) {

    }

    @Override
    public void disconnect(Socket socket) {

    }
}
