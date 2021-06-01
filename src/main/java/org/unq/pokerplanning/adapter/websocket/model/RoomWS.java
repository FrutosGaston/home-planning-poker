package org.unq.pokerplanning.adapter.websocket.model;

import lombok.Builder;
import lombok.Value;
import org.unq.pokerplanning.domain.Room;

import java.time.LocalDateTime;

@Builder
@Value
public class RoomWS {
    Integer id;
    Integer deckId;
    Integer selectedTaskId;
    String title;
    String description;
    LocalDateTime createdAt;

    public static RoomWS of(Room room) {
        return RoomWS.builder()
                .id(room.getId())
                .deckId(room.getDeckId())
                .selectedTaskId(room.getSelectedTaskId())
                .title(room.getTitle())
                .description(room.getDescription())
                .createdAt(room.getCreatedAt())
                .build();
    }
}
