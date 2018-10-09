package ru.boiko.se.chat.packets;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@Getter
@Setter
public class Packet {
    private String id = UUID.randomUUID().toString();
    private String message = "";
    private String login = "";
    private String password = "";
    private PacketType type = PacketType.NONE;
}