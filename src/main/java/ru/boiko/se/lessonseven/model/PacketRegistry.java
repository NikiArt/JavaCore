package ru.boiko.se.lessonseven.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.internal.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PacketRegistry extends Packet {
    {
        setType(PacketType.REGISTRY);
    }

    @Nullable
    private String login;

    @Nullable
    private String password;

}
