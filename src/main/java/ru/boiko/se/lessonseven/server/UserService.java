package ru.boiko.se.lessonseven.server;

import com.sun.istack.internal.Nullable;
import ru.boiko.se.lessonseven.server.model.User;

public interface UserService {

    @Nullable
    User findByLogin(@Nullable String login);

    boolean check(
            @Nullable String login,
            @Nullable String password
    );

    boolean regisrty(
            @Nullable String login,
            @Nullable String password
    );

    boolean exists(@Nullable String login);

    boolean setNick(
            @Nullable String login,
            @Nullable String nick
    );

    boolean setPassword(
            @Nullable String login,
            @Nullable String passwordOld,
            @Nullable String passwordNew
    );

}
