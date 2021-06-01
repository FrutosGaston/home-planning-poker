package org.unq.pokerplanning.application.port.out;

import org.unq.pokerplanning.domain.Room;

import java.util.Optional;

public interface RoomRepository {
    Integer create(Room room);
    Optional<Room> getById(Integer roomId);
    Integer update(Room room);
}
