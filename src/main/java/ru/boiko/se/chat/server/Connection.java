package ru.boiko.se.chat.server;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

@Getter
@Setter
public class Connection {

    @NotNull
    private final String id = UUID.randomUUID().toString();

    @NotNull
    private final Socket socket;

    @Nullable
    private User user;

    public Connection(@NotNull final Socket socket) { this.socket = socket; }

    //@SneakyThrows
    public void send (final String message) {
        final DataOutputStream stream;
        try {
            stream = new DataOutputStream(socket.getOutputStream());
            stream.writeUTF(message);
            stream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
