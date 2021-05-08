package org.unq.pokerplanning.adapter.websocket.model;

import lombok.Builder;
import lombok.Value;
import org.unq.pokerplanning.domain.Estimation;
import org.unq.pokerplanning.domain.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Value
@Builder
public class TaskWS {
    Integer id;
    String title;
    String finalEstimation;
    List<EstimationWS> estimations;
    Integer roomId;
    LocalDateTime createdAt;

    public static TaskWS of(Task task) {
        return TaskWS.builder()
                .id(task.getId())
                .finalEstimation(task.getFinalEstimation())
                .title(task.getTitle())
                .roomId(task.getRoomId())
                .estimations(task.getEstimations().stream()
                        .map(estimation -> EstimationWS.of(estimation))
                        .collect(Collectors.toList()))
                .createdAt(task.getCreatedAt())
                .build();
    }
}