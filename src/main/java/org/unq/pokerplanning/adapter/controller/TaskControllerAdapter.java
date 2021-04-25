package org.unq.pokerplanning.adapter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.unq.pokerplanning.adapter.controller.model.EstimationRest;
import org.unq.pokerplanning.adapter.controller.model.TaskPatchRest;
import org.unq.pokerplanning.adapter.controller.model.TaskRest;
import org.unq.pokerplanning.application.port.in.CreateEstimationCommand;
import org.unq.pokerplanning.application.port.in.FindTaskQuery;
import org.unq.pokerplanning.application.port.in.UpdateTaskCommand;
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

    public TaskControllerAdapter(FindTaskQuery findTaskQuery, CreateEstimationCommand createEstimationCommand, UpdateTaskCommand updateTaskCommand) {
        this.findTaskQuery = findTaskQuery;
        this.createEstimationCommand = createEstimationCommand;
        this.updateTaskCommand = updateTaskCommand;
    }

    @GetMapping
    public List<TaskRest> findTasks(@RequestParam Integer roomId) {
        List<Task> tasks = findTaskQuery.execute(roomId);
        return tasks.stream().map(TaskRest::from).collect(Collectors.toList());
    }

    @PatchMapping("/{taskId}")
    public Integer patchTask(@PathVariable Integer taskId, @RequestBody TaskPatchRest taskPatchRest) {
        return updateTaskCommand.execute(taskPatchRest.toDomain(taskId));
    }

    @PostMapping("/estimations")
    public Integer createEstimation(@RequestBody EstimationRest estimation) {
        return createEstimationCommand.execute(estimation.toDomain());
    }
}

