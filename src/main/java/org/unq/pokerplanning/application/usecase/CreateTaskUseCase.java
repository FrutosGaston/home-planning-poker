package org.unq.pokerplanning.application.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.CreateEstimationCommand;
import org.unq.pokerplanning.application.port.in.CreateTaskCommand;
import org.unq.pokerplanning.application.port.out.EstimationMessenger;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.application.port.out.TaskMessenger;
import org.unq.pokerplanning.application.port.out.TaskRepository;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Task;

import java.util.Optional;

@Component
@Slf4j
public class CreateTaskUseCase implements CreateTaskCommand {

    private final TaskMessenger taskMessenger;
    private final TaskRepository taskRepository;

    public CreateTaskUseCase(TaskMessenger taskMessenger, TaskRepository taskRepository) {
        this.taskMessenger = taskMessenger;
        this.taskRepository = taskRepository;
    }

    @Override
    public Integer execute(Task task) {
        Integer taskId = taskRepository.create(task);
        Task taskWithId = task.withId(taskId);
        taskMessenger.created(taskWithId);
        return taskId;
    }
}
