package org.unq.pokerplanning.application.port.out;

import org.unq.pokerplanning.domain.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> findByRoom(Integer roomId);
    Integer update(Task task);
}
