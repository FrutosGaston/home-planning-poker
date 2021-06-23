package org.unq.pokerplanning.application.port.in;

import org.unq.pokerplanning.domain.Room;

import java.util.Optional;
import java.util.UUID;

public interface FindRoomQuery {
    Optional<Room> execute(UUID uuid);
}
