package ru.boiko.se.chatNew.users;

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
