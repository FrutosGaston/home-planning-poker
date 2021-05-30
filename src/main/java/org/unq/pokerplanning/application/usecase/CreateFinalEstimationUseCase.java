package org.unq.pokerplanning.application.usecase;

import lombok.val;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.CreateFinalEstimationCommand;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.application.port.out.TaskMessenger;
import org.unq.pokerplanning.application.port.out.TaskRepository;
import org.unq.pokerplanning.application.service.EstimationService;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Task;

@Component
public class CreateFinalEstimationUseCase implements CreateFinalEstimationCommand {

    private final EstimationRepository estimationRepository;
    private final TaskMessenger taskMessenger;
    private final TaskRepository taskRepository;
    private final EstimationService estimationService;

    public CreateFinalEstimationUseCase(EstimationRepository estimationRepository, TaskMessenger taskMessenger,
                                        TaskRepository taskRepository, EstimationService estimationService) {
        this.estimationRepository = estimationRepository;
        this.taskMessenger = taskMessenger;
        this.taskRepository = taskRepository;
        this.estimationService = estimationService;
    }

    @Override
    public Integer execute(Estimation estimation) {
        val estimationId = estimationRepository.create(estimation);
        val completedEstimation = estimationService.completeEstimation(estimation.withId(estimationId));

        updateTaskEstimation(estimation, estimationId);

        val taskO = taskRepository.get(estimation.getTaskId());
        taskO.ifPresent(task -> {
            val completedTask = task.withEstimation(completedEstimation);
            taskMessenger.estimated(completedTask);
        });
        return estimationId;
    }

    private void updateTaskEstimation(Estimation estimation, Integer estimationId) {
        val taskToUpdate = Task.builder().id(estimation.getTaskId()).estimationId(estimationId).build();
        taskRepository.update(taskToUpdate);
    }
}
