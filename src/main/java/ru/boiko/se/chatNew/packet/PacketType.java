package ru.boiko.se.chatNew.packet;

public enum PacketType {

    NONE,
    PING,
    RESULT,

    REGISTRY,
    LOGOUT,
    LOGIN,

    MESSAGE,
    PRIVATE_MESSAGE

}
