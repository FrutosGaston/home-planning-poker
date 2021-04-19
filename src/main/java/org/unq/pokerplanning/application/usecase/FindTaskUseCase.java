package org.unq.pokerplanning.application.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.FindTaskQuery;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.application.port.out.TaskRepository;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Task;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FindTaskUseCase implements FindTaskQuery {

    private final TaskRepository taskRepository;
    private final EstimationRepository estimationRepository;

    public FindTaskUseCase(TaskRepository taskRepository, EstimationRepository estimationRepository) {
        this.taskRepository = taskRepository;
        this.estimationRepository = estimationRepository;
    }

    @Override
    public List<Task> execute(Integer roomId) {
        List<Task> tasks = taskRepository.findByRoom(roomId);
        return tasks.stream()
                .map(task -> task.withEstimations(getEstimations(task)))
                .collect(Collectors.toList());
    }

    private List<Estimation> getEstimations(Task task) {
        return estimationRepository.findByTask(task.getId());
    }
}
