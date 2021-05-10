package org.unq.pokerplanning.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import org.unq.pokerplanning.domain.Room;

import java.time.LocalDateTime;

@Value
@Builder
public class RoomRest {

    Integer id;
    String title;
    String description;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt;

    public Room toDomain() {
        return Room.builder()
                .title(this.title)
                .description(this.description)
                .createdAt(this.createdAt)
                .build();
    }

    public static RoomRest from(Room room) {
        return RoomRest.builder()
                .id(room.getId())
                .title(room.getTitle())
                .description(room.getDescription())
                .createdAt(room.getCreatedAt())
                .build();
    }
}
