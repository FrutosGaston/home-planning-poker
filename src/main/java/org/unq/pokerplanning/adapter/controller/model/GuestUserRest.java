package org.unq.pokerplanning.adapter.controller.model;

import lombok.Builder;
import lombok.Value;
import org.unq.pokerplanning.domain.GuestUser;

@Value
@Builder
public class GuestUserRest {
    Integer id;
    String name;
    Integer roomId;
    Boolean spectator;

    public static GuestUserRest from(GuestUser guestUser) {
        return GuestUserRest.builder()
                .name(guestUser.getName())
                .roomId(guestUser.getRoomId())
                .spectator(guestUser.getSpectator())
                .id(guestUser.getId())
                .build();
    }

    public GuestUser toDomain() {
        return GuestUser.builder()
                .id(this.id)
                .name(this.name)
                .roomId(this.roomId)
                .spectator(this.spectator)
                .build();
    }
}
