package ru.boiko.se.lessonseven.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PacketMessage extends Packet{
    {
        setType(PacketType.MESSAGE);
    }

    private String login;
    private String message;
}
