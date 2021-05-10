package org.unq.pokerplanning.application.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.FindRoomQuery;
import org.unq.pokerplanning.application.port.out.RoomRepository;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Room;
import org.unq.pokerplanning.domain.Task;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FindRoomUseCase implements FindRoomQuery {

    private final RoomRepository roomRepository;

    public FindRoomUseCase(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Optional<Room> execute(Integer roomId) {
        return roomRepository.getById(roomId);
    }
}
