package org.unq.pokerplanning.application.usecase;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.UpdateTaskCommand;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.application.port.out.TaskMessenger;
import org.unq.pokerplanning.application.port.out.TaskRepository;
import org.unq.pokerplanning.domain.Task;

@Component
@Slf4j
public class UpdateTaskUseCase implements UpdateTaskCommand {

    private final TaskRepository taskRepository;
    private final TaskMessenger taskMessenger;
    private final EstimationRepository estimationRepository;

    public UpdateTaskUseCase(TaskRepository taskRepository, TaskMessenger taskMessenger, EstimationRepository estimationRepository) {
        this.taskRepository = taskRepository;
        this.taskMessenger = taskMessenger;
        this.estimationRepository = estimationRepository;
    }

    @Override
    public Integer execute(Task task) {
        val taskUpdatedResult = taskRepository.update(task);
        val estimations = estimationRepository.findByTask(task.getId());
        taskMessenger.updated(task.withEstimations(estimations));
        return taskUpdatedResult;
    }
}
