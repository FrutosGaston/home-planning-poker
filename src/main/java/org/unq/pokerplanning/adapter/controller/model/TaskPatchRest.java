package org.unq.pokerplanning.adapter.controller.model;

import lombok.Builder;
import lombok.Value;
import org.unq.pokerplanning.domain.Task;

@Value
@Builder
public class TaskPatchRest {
    Integer id;
    String title;
    Integer roomId;
    String createdAt;

    public Task toDomain(Integer taskId) {
        return Task.builder()
                .id(taskId)
                .roomId(this.roomId)
                .title(this.title)
                .build();
    }
}
