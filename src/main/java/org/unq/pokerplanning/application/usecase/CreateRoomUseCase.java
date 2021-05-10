package org.unq.pokerplanning.application.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.CreateRoomCommand;
import org.unq.pokerplanning.application.port.out.RoomRepository;
import org.unq.pokerplanning.domain.Room;

@Component
@Slf4j
public class CreateRoomUseCase implements CreateRoomCommand {

    private final RoomRepository roomRepository;

    public CreateRoomUseCase(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Integer execute(Room room) {
        return roomRepository.create(room);
    }
}
