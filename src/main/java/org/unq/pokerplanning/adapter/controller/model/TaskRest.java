package org.unq.pokerplanning.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import org.unq.pokerplanning.domain.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Value
@Builder
public class TaskRest {
    Integer id;
    Integer roomId;
    String title;
    String finalEstimation;
    List<EstimationRest> estimations;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt;

    public static TaskRest from(Task task) {
        return TaskRest.builder()
                .id(task.getId())
                .roomId(task.getRoomId())
                .title(task.getTitle())
                .finalEstimation(task.getFinalEstimation())
                .createdAt(task.getCreatedAt())
                .estimations(task.getEstimations().stream()
                        .map(EstimationRest::from)
                        .collect(Collectors.toList()))
                .build();
    }

    public Task toDomain() {
        return Task.builder()
                .roomId(this.roomId)
                .title(this.title)
                .finalEstimation(this.finalEstimation)
                .build();
    }

}
