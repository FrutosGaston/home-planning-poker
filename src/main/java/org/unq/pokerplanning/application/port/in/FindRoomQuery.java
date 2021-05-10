package org.unq.pokerplanning.application.port.in;

import org.unq.pokerplanning.domain.Room;

import java.util.Optional;

public interface FindRoomQuery {
    Optional<Room> execute(Integer roomId);
}
