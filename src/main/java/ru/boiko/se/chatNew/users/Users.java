package ru.boiko.se.chatNew.users;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class Users {
    private static Users instance;

    @NotNull
    private Map<String, User> users = new LinkedHashMap<>();


    private Users() {
    }

    public static synchronized Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    public User findByLogin(String login) {
        if (login == null || login.isEmpty()) return null;
        return users.get(login);
    }

    public boolean check(@Nullable String login, @Nullable String password) {
        if (login == null || login.isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;
        @Nullable final User user = findByLogin(login);
        if (user == null) return false;
        return password.equals(user.getPassword());
    }

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

    public boolean exists(String login) {
        if (login == null || login.isEmpty()) return false;
        return users.containsKey(login);
    }

    public boolean setNick(String login, String nick) {
        if (login == null || login.isEmpty()) return false;
        if (nick == null || nick.isEmpty()) return false;
        @Nullable final User user = findByLogin(login);
        if (user == null) return false;
        user.setNick(nick);
        return true;
    }

    public boolean setPassword(String login, String passwordOld, String passwordNew) {
        if(!check(login, passwordOld)) return false;
        if(passwordNew == null || passwordNew.isEmpty()) return false;
        @Nullable final User user = findByLogin(login);
        user.setPassword(passwordNew);
        return true;
    }
}
