package org.unq.pokerplanning.application.port.out;

import org.unq.pokerplanning.domain.Room;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository {
    Integer create(Room room);
    Optional<Room> getById(Integer roomId);
    Optional<Room> getByUUID(UUID uuid);
    Integer update(Room room);
}
