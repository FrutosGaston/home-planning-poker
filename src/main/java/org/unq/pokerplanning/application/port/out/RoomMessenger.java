package org.unq.pokerplanning.application.port.out;

import org.unq.pokerplanning.domain.Room;

public interface RoomMessenger {
    void updated(Room room);
}
