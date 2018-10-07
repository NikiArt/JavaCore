package ru.boiko.se.lessonseven.server;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.net.Socket;
import java.util.List;


public interface ConnectionService {

    @NotNull
    List<Connection> connections();

    @Nullable
    Connection get(Socket socket);

    void add(@Nullable Socket socket);

    void  remove(@Nullable Socket socket);

    void setLogin(@Nullable Socket socket, @Nullable String login);

    void sendResult(@Nullable Socket socket, @Nullable Boolean success);

    void sendMessage(@Nullable Connection connection, @Nullable String login, @Nullable String message);

    void disconnect(@Nullable Socket socket);
}
