package org.unq.pokerplanning.application.port.in;

import org.unq.pokerplanning.domain.Room;

public interface UpdateRoomCommand {
    Integer execute(Room room);
}
