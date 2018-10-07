package ru.boiko.se.lessonseven.server.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.internal.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PacketBroadcast extends Packet{

    {
        setType(PacketType.BROADCAST);
    }

    @Nullable
    private String message = "";
}
