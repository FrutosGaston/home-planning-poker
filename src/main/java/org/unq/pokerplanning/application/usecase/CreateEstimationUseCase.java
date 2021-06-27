package org.unq.pokerplanning.application.usecase;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.CreateEstimationCommand;
import org.unq.pokerplanning.application.port.out.*;
import org.unq.pokerplanning.application.service.EstimationService;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Task;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CreateEstimationUseCase implements CreateEstimationCommand {

    private final EstimationRepository estimationRepository;
    private final EstimationMessenger estimationMessenger;
    private final TaskMessenger taskMessenger;
    private final EstimationService estimationService;
    private final TaskRepository taskRepository;

    public CreateEstimationUseCase(EstimationRepository estimationRepository, EstimationMessenger estimationMessenger,
                                   TaskMessenger taskMessenger, TaskRepository taskRepository, EstimationService estimationService) {
        this.estimationRepository = estimationRepository;
        this.estimationMessenger = estimationMessenger;
        this.taskMessenger = taskMessenger;
        this.estimationService = estimationService;
        this.taskRepository = taskRepository;
    }

    @Override
    public Integer execute(Estimation estimation) {
        invalidatePreviousEstimations(estimation);

        val estimationId = estimationRepository.create(estimation);
        val completedEstimation = estimationService.completeEstimation(estimation
                .withId(estimationId)
                .withActive(true));

        val taskO = taskRepository.get(estimation.getTaskId());
        taskO.ifPresent(task -> estimationMessenger.created(completedEstimation, task.getRoomId()));

        return estimationId;
    }

    private void invalidatePreviousEstimations(Estimation estimation) {
        val taskId = estimation.getTaskId();
        estimationRepository.invalidateAllForGuestUser(taskId, estimation.getGuestUserId());
        val estimations = estimationRepository.findByTask(taskId);
        val completedEstimations = estimations.stream().map(estimationService::completeEstimation).collect(Collectors.toList());
        val taskO = taskRepository.get(taskId).map(task -> task.withEstimations(completedEstimations));
        taskO.ifPresent(taskMessenger::invalidated);
    }
}
