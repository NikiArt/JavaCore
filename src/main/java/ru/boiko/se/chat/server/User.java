package ru.boiko.se.chat.server;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String login = "";
    private String password = "";
    private String nick = "";
    private String email = "";

}
