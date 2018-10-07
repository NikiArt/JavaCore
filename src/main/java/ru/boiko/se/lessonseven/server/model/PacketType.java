package ru.boiko.se.lessonseven.server.model;

public enum PacketType {

    NONE,
    PING,
    RESULT,

    REGISTRY,
    LOGOUT,
    LOGIN,

    MESSAGE,
    BROADCAST

}
