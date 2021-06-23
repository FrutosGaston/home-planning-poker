package org.unq.pokerplanning.application.usecase;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.UpdateRoomCommand;
import org.unq.pokerplanning.application.port.out.RoomMessenger;
import org.unq.pokerplanning.application.port.out.RoomRepository;
import org.unq.pokerplanning.domain.Room;

@Component
@Slf4j
public class UpdateRoomUseCase implements UpdateRoomCommand {

    private final RoomRepository roomRepository;
    private final RoomMessenger roomMessenger;

    public UpdateRoomUseCase(RoomRepository roomRepository, RoomMessenger roomMessenger) {
        this.roomRepository = roomRepository;
        this.roomMessenger = roomMessenger;
    }

    @Override
    public Integer execute(Room room) {
        val updateResult = roomRepository.update(room);
        roomRepository.getById(room.getId())
                .ifPresent(roomMessenger::updated);
        return updateResult;
    }
}
