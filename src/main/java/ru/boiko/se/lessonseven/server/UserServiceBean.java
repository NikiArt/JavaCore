package ru.boiko.se.lessonseven.server;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import lombok.NoArgsConstructor;
import ru.boiko.se.lessonseven.server.model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.jws.soap.SOAPBinding;
import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor
@ApplicationScoped
public class UserServiceBean implements UserService {

    @NotNull
    private Map<String, User> users = new LinkedHashMap<>();

    @PostConstruct
    private void init() {
        regisrty("admin", "admin");
        regisrty("test", "test");
    }

    @Override
    public User findByLogin(String login) {
        if (login == null || login.isEmpty()) return null;
        return users.get(login);
    }

    @Override
    public boolean check(@Nullable String login, @Nullable String password) {
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        @Nullable final User user = findByLogin(login);
        if (user == null) return false;
        return password.equals(user.getPassword());
    }

    @Override
    public boolean regisrty(String login, String password) {
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        if(exists(login)) return  false;
        @NotNull final User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        users.put(login, user);
        System.out.println("Added new user with login " + login);
        return true;

    }

    @Override
    public boolean exists(String login) {
        if (login == null || login.isEmpty()) return false;
        return users.containsKey(login);
    }

    @Override
    public boolean setNick(String login, String nick) {
        if (login == null || login.isEmpty()) return false;
        if (nick == null || nick.isEmpty()) return false;
        @Nullable final User user = findByLogin(login);
        if (user == null) return false;
        user.setNick(nick);
        return true;
    }

    @Override
    public boolean setPassword(String login, String passwordOld, String passwordNew) {
        if(!check(login, passwordOld)) return false;
        if(passwordNew == null || passwordNew.isEmpty()) return false;
        @Nullable final User user = findByLogin(login);
        user.setPassword(passwordNew);
        return true;
    }
}
