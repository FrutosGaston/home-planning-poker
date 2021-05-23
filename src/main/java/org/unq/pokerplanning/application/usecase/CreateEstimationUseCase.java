package org.unq.pokerplanning.application.usecase;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.CreateEstimationCommand;
import org.unq.pokerplanning.application.port.out.CardRepository;
import org.unq.pokerplanning.application.port.out.EstimationMessenger;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.application.port.out.TaskRepository;
import org.unq.pokerplanning.application.service.EstimationService;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Task;

import java.util.Optional;

@Component
@Slf4j
public class CreateEstimationUseCase implements CreateEstimationCommand {

    private final EstimationRepository estimationRepository;
    private final EstimationMessenger estimationMessenger;
    private final EstimationService estimationService;
    private final TaskRepository taskRepository;

    public CreateEstimationUseCase(EstimationRepository estimationRepository, EstimationMessenger estimationMessenger,
                                   TaskRepository taskRepository, EstimationService estimationService) {
        this.estimationRepository = estimationRepository;
        this.estimationMessenger = estimationMessenger;
        this.estimationService = estimationService;
        this.taskRepository = taskRepository;
    }

    @Override
    public Integer execute(Estimation estimation) {
        Integer estimationId = estimationRepository.create(estimation);
        val completedEstimation = estimationService.completeEstimation(estimation.withId(estimationId));

        Optional<Task> taskO = taskRepository.get(estimation.getTaskId());
        taskO.ifPresent(task -> estimationMessenger.created(completedEstimation, task.getRoomId()));

        return estimationId;
    }
}
