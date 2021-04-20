package org.unq.pokerplanning.adapter.controller.model;

import lombok.Builder;
import lombok.Value;
import org.unq.pokerplanning.domain.Task;

@Value
@Builder
public class TaskPatchRest {
    String title;
    String finalEstimation;

    public Task toDomain(Integer taskId) {
        return Task.builder()
                .id(taskId)
                .finalEstimation(this.finalEstimation)
                .title(this.title)
                .build();
    }
}
