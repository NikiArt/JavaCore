package ru.boiko.se.lessonseven.server.model;


import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@Getter
@Setter
@ApplicationScoped
public class Packet {
    private String id = UUID.randomUUID().toString();
    private PacketType type = PacketType.NONE;
}
