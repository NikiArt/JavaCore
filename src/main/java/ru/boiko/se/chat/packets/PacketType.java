package ru.boiko.se.chat.packets;

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
