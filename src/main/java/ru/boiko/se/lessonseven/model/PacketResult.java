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
public final class PacketResult extends Packet {
    {
        setType(PacketType.RESULT);
    }

    public PacketResult(Boolean success) { this.success = success; }

    private Boolean success = true;
}
