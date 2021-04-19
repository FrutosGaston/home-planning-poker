package org.unq.pokerplanning.adapter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.unq.pokerplanning.adapter.controller.model.EstimationRest;
import org.unq.pokerplanning.adapter.controller.model.TaskRest;
import org.unq.pokerplanning.application.port.in.CreateEstimationCommand;
import org.unq.pokerplanning.application.port.in.FindTaskQuery;
import org.unq.pokerplanning.domain.Task;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tasks")
@Slf4j
public final class TaskControllerAdapter {

    private final FindTaskQuery findTaskQuery;
    private final CreateEstimationCommand createEstimationCommand;

    public TaskControllerAdapter(FindTaskQuery findTaskQuery, CreateEstimationCommand createEstimationCommand) {
        this.findTaskQuery = findTaskQuery;
        this.createEstimationCommand = createEstimationCommand;
    }

    @GetMapping()
    public List<TaskRest> getGuestUser(@RequestParam Integer roomId) {
        List<Task> tasks = findTaskQuery.execute(roomId);
        return tasks.stream().map(TaskRest::from).collect(Collectors.toList());
    }

    @PostMapping("/estimations")
    public Integer getGuestUser(@RequestBody EstimationRest estimation) {
        return createEstimationCommand.execute(estimation.toDomain());

    }
}

