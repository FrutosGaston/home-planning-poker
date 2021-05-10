package org.unq.pokerplanning.application.port.in;

import org.unq.pokerplanning.domain.Room;

public interface CreateRoomCommand {
    Integer execute(Room room);
}
