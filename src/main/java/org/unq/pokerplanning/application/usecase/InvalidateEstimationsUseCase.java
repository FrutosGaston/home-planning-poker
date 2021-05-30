package org.unq.pokerplanning.application.usecase;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.InvalidateEstimationsCommand;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.application.port.out.TaskMessenger;
import org.unq.pokerplanning.application.port.out.TaskRepository;
import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.config.exception.NotFoundException;
import org.unq.pokerplanning.domain.Task;

@Component
@Slf4j
public class InvalidateEstimationsUseCase implements InvalidateEstimationsCommand {

    private final TaskMessenger taskMessenger;
    private final EstimationRepository estimationRepository;
    private final TaskRepository taskRepository;

    public InvalidateEstimationsUseCase(TaskMessenger taskMessenger, EstimationRepository estimationRepository, TaskRepository taskRepository) {
        this.taskMessenger = taskMessenger;
        this.estimationRepository = estimationRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void execute(Integer taskId) {
        val taskUpdatedResult = estimationRepository.invalidateAll(taskId);
        if (taskUpdatedResult < 1) throw new NotFoundException(ErrorCode.INTERNAL_ERROR);

        val estimations = estimationRepository.findByTask(taskId);
        val taskO = taskRepository.get(taskId).map(task -> task.withEstimations(estimations));

        taskO.ifPresent(taskMessenger::invalidated);
    }

}
