package org.unq.pokerplanning.adapter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.unq.pokerplanning.adapter.controller.model.EstimationRest;
import org.unq.pokerplanning.adapter.controller.model.TaskPatchRest;
import org.unq.pokerplanning.adapter.controller.model.TaskRest;
import org.unq.pokerplanning.application.port.in.*;
import org.unq.pokerplanning.domain.Task;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tasks")
@Slf4j
public final class TaskControllerAdapter {

    private final FindTaskQuery findTaskQuery;
    private final CreateEstimationCommand createEstimationCommand;
    private final UpdateTaskCommand updateTaskCommand;
    private final CreateFinalEstimationCommand createFinalEstimationCommand;
    private final CreateTaskCommand createTaskCommand;

    public TaskControllerAdapter(FindTaskQuery findTaskQuery, CreateEstimationCommand createEstimationCommand,
                                 UpdateTaskCommand updateTaskCommand, CreateFinalEstimationCommand createFinalEstimationCommand,
                                 CreateTaskCommand createTaskCommand) {
        this.findTaskQuery = findTaskQuery;
        this.createEstimationCommand = createEstimationCommand;
        this.updateTaskCommand = updateTaskCommand;
        this.createFinalEstimationCommand = createFinalEstimationCommand;
        this.createTaskCommand = createTaskCommand;
    }

    @GetMapping
    public List<TaskRest> findTasks(@RequestParam Integer roomId) {
        List<Task> tasks = findTaskQuery.execute(roomId);
        return tasks.stream().map(TaskRest::from).collect(Collectors.toList());
    }

    @PostMapping
    public Integer createTask(@RequestBody TaskRest taskRest) {
        return createTaskCommand.execute(taskRest.toDomain());
    }

    @PatchMapping("/{taskId}")
    public Integer patchTask(@PathVariable Integer taskId, @RequestBody TaskPatchRest taskPatchRest) {
        return updateTaskCommand.execute(taskPatchRest.toDomain(taskId));
    }

    @PostMapping("/final-estimations")
    public Integer finalEstimation(@RequestBody EstimationRest estimation) {
        return createFinalEstimationCommand.execute(estimation.toDomain());
    }

    @PostMapping("/estimations")
    public Integer createEstimation(@RequestBody EstimationRest estimation) {
        return createEstimationCommand.execute(estimation.toDomain());
    }
}

