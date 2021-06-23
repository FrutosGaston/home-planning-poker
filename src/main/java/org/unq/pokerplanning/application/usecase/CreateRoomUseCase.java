package org.unq.pokerplanning.application.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.CreateRoomCommand;
import org.unq.pokerplanning.application.port.out.RoomRepository;
import org.unq.pokerplanning.domain.Room;

import java.util.Optional;

@Component
@Slf4j
public class CreateRoomUseCase implements CreateRoomCommand {

    private final RoomRepository roomRepository;

    public CreateRoomUseCase(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Optional<Room> execute(Room room) {
        Integer id = roomRepository.create(room);
        return roomRepository.getById(id);
    }
}
