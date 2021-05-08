package org.unq.pokerplanning.application.usecase;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.CreateEstimationCommand;
import org.unq.pokerplanning.application.port.out.EstimationMessenger;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.application.port.out.TaskRepository;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Task;

import java.util.Optional;

@Component
@Slf4j
public class CreateEstimationUseCase implements CreateEstimationCommand {

    private final EstimationRepository estimationRepository;
    private final EstimationMessenger estimationMessenger;
    private final TaskRepository taskRepository;

    public CreateEstimationUseCase(EstimationRepository estimationRepository, EstimationMessenger estimationMessenger, TaskRepository taskRepository) {
        this.estimationRepository = estimationRepository;
        this.estimationMessenger = estimationMessenger;
        this.taskRepository = taskRepository;
    }

    @Override
    public Integer execute(Estimation estimation) {
        Integer estimationId = estimationRepository.create(estimation);
        Estimation estimationWithId = estimation.withId(estimationId);
        Optional<Task> taskO = taskRepository.get(estimation.getTaskId());
        taskO.ifPresent(task -> estimationMessenger.created(estimationWithId, task.getRoomId()));
        return estimationId;
    }
}
