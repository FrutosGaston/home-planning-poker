package org.unq.pokerplanning.adapter.websocket.model;

import lombok.Builder;
import lombok.Value;
import org.unq.pokerplanning.adapter.controller.model.GuestUserRest;
import org.unq.pokerplanning.domain.GuestUser;

import java.time.LocalDateTime;

@Value
@Builder
public class GuestUserWS {
    Integer id;
    String name;
    Integer roomId;
    LocalDateTime createdAt;

    public static GuestUserWS from(GuestUser guestUser) {
        return GuestUserWS.builder()
                .name(guestUser.getName())
                .roomId(guestUser.getRoomId())
                .id(guestUser.getId())
                .createdAt(guestUser.getCreatedAt())
                .build();
    }
}
