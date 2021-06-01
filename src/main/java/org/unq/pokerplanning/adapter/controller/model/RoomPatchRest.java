package org.unq.pokerplanning.adapter.controller.model;

import lombok.Builder;
import lombok.Value;
import org.unq.pokerplanning.domain.Room;

@Value
@Builder
public class RoomPatchRest {

    Integer selectedTaskId;

    public Room toDomain(Integer roomId) {
        return Room.builder()
                .id(roomId)
                .selectedTaskId(this.selectedTaskId)
                .build();
    }
}
