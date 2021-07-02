package org.unq.pokerplanning.application.usecase;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.CreateRoomCommand;
import org.unq.pokerplanning.application.port.out.RoomRepository;
import org.unq.pokerplanning.application.port.out.TaskRepository;
import org.unq.pokerplanning.domain.Room;
import org.unq.pokerplanning.domain.Task;

import java.util.Optional;

@Component
@Slf4j
public class CreateRoomUseCase implements CreateRoomCommand {

    private final RoomRepository roomRepository;
    private final TaskRepository taskRepository;

    public CreateRoomUseCase(RoomRepository roomRepository, TaskRepository taskRepository) {
        this.roomRepository = roomRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Room> execute(Room room) {
        val id = roomRepository.create(room);

        val defaultTask = Task.builder().title(room.getTitle()).roomId(id).build();
        taskRepository.create(defaultTask);

        return roomRepository.getById(id);
    }
}
