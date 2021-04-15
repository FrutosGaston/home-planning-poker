package org.unq.pokerplanning.adapter.controller.model;

import lombok.Builder;
import lombok.Value;
import org.unq.pokerplanning.domain.GuestUser;

@Value
@Builder
public class GuestUserRest {
    Long id;
    String name;
    Long roomId;

    public static GuestUserRest from(GuestUser guestUser) {
        return GuestUserRest.builder()
                .name(guestUser.getName())
                .roomId(guestUser.getRoomId())
                .id(guestUser.getId())
                .build();
    }

    public GuestUser toDomain() {
        return GuestUser.builder()
                .id(this.id)
                .name(this.name)
                .roomId(this.roomId)
                .build();
    }
}
